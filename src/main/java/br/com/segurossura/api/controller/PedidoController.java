package br.com.segurossura.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.segurossura.api.domains.pedido.Pedido;
import br.com.segurossura.api.services.PedidoService;

@RestController
@RequestMapping("/gestao-pedido/pedido")
public class PedidoController {

	@Autowired
	private PedidoService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> findById(@PathVariable Long id) {
		Pedido retorno;
		retorno = service.find(id);
		return ResponseEntity.ok().body(retorno);
	}
	
		
}
