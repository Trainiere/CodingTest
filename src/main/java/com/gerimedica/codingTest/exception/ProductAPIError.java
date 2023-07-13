package com.gerimedica.codingTest.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAPIError {

	private HttpStatus statusCode;
	private String errorMessage;

}
