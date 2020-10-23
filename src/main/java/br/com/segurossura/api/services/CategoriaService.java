package br.com.segurossura.api.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.segurossura.api.domains.produto.Categoria;
import br.com.segurossura.api.dto.produto.CategoriaDto;
import br.com.segurossura.api.repository.CategoriaRepository;
import br.com.segurossura.api.services.exception.DataIntegrityException;
import br.com.segurossura.api.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repository;
	
	public List<Categoria> findAll() {
		return repository.findAll();
	}

	public Categoria find(Long id) throws ObjectNotFoundException {
		Optional<Categoria> retorno = repository.findById(id);
		return retorno.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ! Id:" + id + " Tipo:" + Categoria.class.getName()));
	}

	@Transactional
	public Categoria insert(Categoria obj) {
		//obj.setId(null);
		return repository.save(obj);
	}

	@Transactional
	public Categoria update(Categoria obj) {
		Categoria objNew = find(obj.getCodigo());
		updateData(objNew, obj);
		return repository.save(objNew);
	}

	public void delete(Long id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	
	private void updateData(Categoria objNew, Categoria obj) {
		objNew.setDescricao(obj.getDescricao());
	}
	
	
	 public Categoria armazenar(CategoriaDto dto) {

	        Categoria categoria = new Categoria(dto.descricao);
	        repository.save(categoria);

	        return categoria;
	    }
	 
	 public Categoria findByNome(String nome) {
		 Categoria cat = new Categoria();
		 return cat;
	 }
	
}
