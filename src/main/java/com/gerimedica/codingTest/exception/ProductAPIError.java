package com.gerimedica.codingTest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAPIError {

	private String statusCode;
	private String errorMessage;

}
