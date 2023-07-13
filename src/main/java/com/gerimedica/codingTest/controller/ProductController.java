package com.gerimedica.codingTest.controller;

import java.io.StringWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gerimedica.codingTest.service.ProductService;
import com.gerimedica.codingTest.to.ProductTO;
import com.gerimedica.codingTest.to.Response;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

	Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	public ProductService productService;

	/**
	 * Upload a csv file to DB * @return
	 */
	@PostMapping(path = "/upload" , consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		productService.createAllproduct(file);
		return ResponseEntity.status(HttpStatus.CREATED).body("All records added successfully");
	}

	/**
	 * Find all products in json
	 * 
	 * @return
	 */
	@GetMapping(path = "/fetchAll")
	public ResponseEntity<List<ProductTO>> findAllProducts() {
		return ResponseEntity.ok(productService.findAllProductsInJson());
	}

	/**
	 * Fetch all products in a csv file.
	 * 
	 * @return
	 */
	@GetMapping(path = "/download", produces = "text/csv")
	public ResponseEntity<String> fetchAllDataAsCsv() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException{
		StringWriter initialString = productService.findAllProductsInCSV();
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=exercise.csv")
				.contentType(MediaType.parseMediaType("application/csv")).body(initialString.toString());
	}

	/**
	 * Find product with code
	 * 
	 * @param code
	 * @return
	 */
	@GetMapping(path = "/code/{code}")
	public ResponseEntity<ProductTO> findProductWithCode(@PathVariable String code) {
		return ResponseEntity.ok(productService.findProductWithCode(code));
	}

	/**
	 * Delete all products
	 * 
	 * @return
	 */
	@DeleteMapping(path = "/deleteAll")
	public ResponseEntity<String> deleteAllProducts() {
		productService.deleteAllProducts();
		return ResponseEntity.status(HttpStatus.OK).body("All products deleted successfully");
	}
}
