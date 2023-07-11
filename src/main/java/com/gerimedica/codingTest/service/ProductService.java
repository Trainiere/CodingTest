package com.gerimedica.codingTest.service;

import java.io.StringWriter;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gerimedica.codingTest.to.ProductTO;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@Service
public interface ProductService {
	void createAllproduct(MultipartFile file);

	ProductTO findProductWithCode(String code);

	List<ProductTO> findAllProductsInJson();

	StringWriter findAllProductsInCSV() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException;

	void deleteAllProducts();

}
