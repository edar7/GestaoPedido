package br.com.segurossura.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.segurossura.api.domains.produto.Produto;
import br.com.segurossura.api.repository.CategoriaRepository;
import br.com.segurossura.api.repository.ProdutoRepository;
import br.com.segurossura.api.services.exception.ObjectNotFoundException;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	public Produto findByID(Long id) {
		Optional<Produto> retorno = produtoRepository.findById(id);
		return retorno.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ! Id:" + id + " Tipo:" + Produto.class.getName()));
	}


}
