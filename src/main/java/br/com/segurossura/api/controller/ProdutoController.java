package br.com.segurossura.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.segurossura.api.domains.produto.Produto;
import br.com.segurossura.api.services.ProdutoService;

@RestController
@RequestMapping("/gestao-pedido/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> findById(@PathVariable Long id) {
		Produto retorno;
		retorno = service.findByID(id);
		return ResponseEntity.ok().body(retorno);
	}

}
