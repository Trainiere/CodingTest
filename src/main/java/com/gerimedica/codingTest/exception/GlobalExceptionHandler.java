package com.gerimedica.codingTest.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gerimedica.codingTest.to.ProductTO;
import com.gerimedica.codingTest.to.Response;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler
	public ResponseEntity<ProductTO>> handleProductNotFoundException(ProductNotFoundException ex) {
		return ResponseEntity.new Response<ProductTO>(ex.getMessage(), null, ex);
	}

}
