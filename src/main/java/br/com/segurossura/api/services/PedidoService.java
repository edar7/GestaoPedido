package br.com.segurossura.api.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.segurossura.api.domains.pedido.ItemPedido;
import br.com.segurossura.api.domains.pedido.Pedido;
import br.com.segurossura.api.repository.ItemPedidoRepository;
import br.com.segurossura.api.repository.PedidoRepository;
import br.com.segurossura.api.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;

	@Autowired
	private ProdutoService produtoService;

	private ItemPedidoRepository itemPedidoRepository;

	public List<Pedido> findAll() {
		return repository.findAll();
	}
	
	public Pedido find(Long id) throws ObjectNotFoundException {
		Optional<Pedido> retorno = repository.findById(id);
		return retorno.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ! Id:" + id + " Tipo:" + Pedido.class.getName()));
	}

	public Pedido insert(Pedido obj) {
		obj.setCodigo(null);
		obj.setDataCriacao(new Date());
		
		
		obj = repository.save(obj);
		
		for (ItemPedido ip : obj.getItens()) {
			
			ip.setValorUnitario(produtoService.findByID(ip.getProduto().getCodigo()).getValorUnitario());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}

}
