package com.gerimedica.codingTest.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import com.gerimedica.codingTest.to.ProductTO;
import com.gerimedica.codingTest.to.Response;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ProductAPIError> handleProductNotFoundException(ProductNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ProductAPIError(HttpStatus.NOT_FOUND, ex.getMessage()));
	}
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<ProductAPIError> handleProductException(ProductException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProductAPIError(HttpStatus.BAD_REQUEST, ex.getMessage()));
	}
	
	@ExceptionHandler({CsvDataTypeMismatchException.class,
			CsvRequiredFieldEmptyException.class,
			Exception.class})
	public ResponseEntity<ProductAPIError> handleProductException(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ProductAPIError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return ResponseEntity.status(status).body(new ProductAPIError(status,ex.getMessage()));
	}

}
