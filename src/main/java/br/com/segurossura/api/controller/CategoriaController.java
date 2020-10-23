package br.com.segurossura.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.segurossura.api.domains.produto.Categoria;
import br.com.segurossura.api.dto.produto.CategoriaDto;
import br.com.segurossura.api.services.CategoriaService;

@RestController
@RequestMapping("/gestao-pedido/categoria")
public class CategoriaController {
	
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> findById(@PathVariable Long id) {
		Categoria retorno;
		retorno = service.find(id);
		return ResponseEntity.ok().body(retorno);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
    public ResponseEntity<?> post(@RequestBody CategoriaDto dto) {
        Categoria retorno = service.armazenar(dto);
        return ResponseEntity.created(URI.create("/gestao-pedido/categoria/" + retorno.getCodigo())).build();
    }
	
	
	
}
