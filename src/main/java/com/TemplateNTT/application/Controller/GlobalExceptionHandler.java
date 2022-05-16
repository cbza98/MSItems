package com.TemplateNTT.application.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.MarkerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;


import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(WebExchangeBindException.class)
	public Mono<ResponseEntity<Map<String, Object>>> handlerException(WebExchangeBindException ex){
		
		Map<String, Object>	response = new HashMap<>();
		
		 log.warn(MarkerFactory.getMarker("VALID"),ex.getMessage());
		 return Mono.just(ex)
	    	     .flatMap(e -> Mono.just(e.getFieldErrors()))
	    	     .flatMapMany(Flux::fromIterable)
	    	     .map(fieldError->"The field:" + fieldError.getField() +" "+ fieldError.getDefaultMessage())
	    	     .collectList()
	    	     .flatMap(list->{
	    	 
	    	    	response.put("errors", list);
	 				response.put("timestamp", new Date());
	 				response.put("status", HttpStatus.BAD_REQUEST.value());
	 				
	 				return Mono.just(ResponseEntity.badRequest().body(response));
	    	    	 
	    	     });
	}
	
	@ExceptionHandler(EntityNotExistsException.class)
	public Mono<ResponseEntity<Map<String, Object>>> handlerException(EntityNotExistsException ex){
		
		 Map<String, Object>	response = new HashMap<>();
		
		 log.warn(MarkerFactory.getMarker("VALID"),ex.getMessage());
		 return Mono.just(ex)
	    	     .map(error->error.getMessage())    
	    	     .flatMap(msg->{
	    	 
	    	    	response.put("errors", msg);
	 				response.put("timestamp", new Date());
	 				response.put("status", HttpStatus.NOT_FOUND.value());
	 				
	 				return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(response));
	    	    	 
	    	     });
	}
	
	
}
