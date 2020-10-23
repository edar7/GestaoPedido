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

import br.com.segurossura.api.domains.cliente.Cliente;
import br.com.segurossura.api.dto.Cliente.ClienteDto;
import br.com.segurossura.api.services.ClienteService;

@RestController
@RequestMapping("/gestao-pedido/cliente")
public class ClienteController {

	@Autowired
	private ClienteService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> findById(@PathVariable Long id) {
		Cliente retorno;
		retorno = service.find(id);
		return ResponseEntity.ok().body(retorno);
	}
	
	@PostMapping()
    ResponseEntity<?> post(@RequestBody ClienteDto clienteDto)
    {
        Cliente cliente = service.armazenar(clienteDto);

        return ResponseEntity.created(URI.create("/gestao-pedido/cliente" + cliente.getCodigo())).build();
    }
	
}
