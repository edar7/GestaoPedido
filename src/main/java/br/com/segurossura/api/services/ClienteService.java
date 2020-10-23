package br.com.segurossura.api.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.segurossura.api.domains.cliente.Cliente;
import br.com.segurossura.api.domains.endereco.Endereco;
import br.com.segurossura.api.dto.Cliente.ClienteDto;
import br.com.segurossura.api.repository.CidadeRepository;
import br.com.segurossura.api.repository.ClienteRepository;
import br.com.segurossura.api.repository.EnderecoRepository;
import br.com.segurossura.api.services.exception.DataIntegrityException;
import br.com.segurossura.api.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;

	@Autowired
	private EnderecoRepository enderecoRepo;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente find(Long id) throws ObjectNotFoundException {
		Optional<Cliente> retorno = repository.findById(id);
		return retorno.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ! Id:" + id + " Tipo:" + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setCodigo(null);
		obj = repository.save(obj);
		enderecoRepo.saveAll(obj.getEndereco());
		return obj;
	}

	@Transactional
	public Cliente update(Cliente obj) {
		Cliente objNew = find(obj.getCodigo());
		updateData(objNew, obj);
		return repository.save(objNew);
	}

	public void delete(Long id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	

	private void updateData(Cliente objNew, Cliente obj) {
		objNew.setNome(obj.getNome());
		objNew.setEmail(obj.getEmail());
	}
	
	 public Cliente armazenar(ClienteDto clienteDto) {

        String senhaEncriptada = bCryptPasswordEncoder.encode(clienteDto.senha);
        Endereco endereco = new Endereco(clienteDto.rua, clienteDto.cidade, clienteDto.bairro, clienteDto.cep, clienteDto.estado);
        Cliente cliente = new Cliente(clienteDto.nome, clienteDto.email, senhaEncriptada, endereco);

        repository.save(cliente);

        return cliente;
    }
}
