package br.com.segurossura.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.segurossura.api.util.Excecao;

@RestControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler
	@ResponseBody
	ResponseEntity<?> handleException(Exception ex){
		
		if(ex instanceof Excecao)
			return ResponseEntity.badRequest().body(ex.getMessage());
		
		return ResponseEntity.status(500).body("Erro inexperado!");
			
	}

}
