package com.diego.workshopmongo.resources.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.diego.workshopmongo.services.exception.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException error, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;//404 
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não Encontrado", error.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
		
	}
	
}
