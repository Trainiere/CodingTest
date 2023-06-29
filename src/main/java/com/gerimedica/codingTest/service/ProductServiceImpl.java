package com.gerimedica.codingTest.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gerimedica.codingTest.controller.ProductController;
import com.gerimedica.codingTest.dao.ProductDao;
import com.gerimedica.codingTest.exception.ProductNotFoundException;
import com.gerimedica.codingTest.model.Product;
import com.gerimedica.codingTest.to.ProductTO;
import com.gerimedica.codingTest.to.Response;

@Service
public class ProductServiceImpl implements ProductService {

	Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductDao employeeDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Response<ProductTO> createAllproduct(MultipartFile file) {
		Response<ProductTO> response = new Response<>();
		List<ProductTO> inputList = new ArrayList<ProductTO>();
		boolean isValid = true;
		if (file == null || !file.getOriginalFilename().endsWith(".csv")) {
			log.error("File is not a csv file\n");
			response.setMessage("File is not a csv file");
			isValid = false;
		}
		if (file == null || file.getSize() == 0) {
			log.error("Empty File is uploaded\n");
			response.setMessage("Empty File is uploaded");
			isValid = false;
		}
		if (isValid) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
				inputList = br.lines().skip(1).map(mapToProduct).collect(Collectors.toList());
				br.close();
			} catch (IOException e) {
				log.error("Error occured while reading csv file\n" + e);
				response.setMessage("Error occured while reading csv file");
				response.setException(e);
			}
			employeeDao.saveAll(
					inputList.stream().map(emp -> modelMapper.map(emp, Product.class)).collect(Collectors.toList()));
			response.setMessage("All products created successfully");
		}
		return response;
	}

	@Override
	public Response<ProductTO> findProductWithCode(String code) {
		Response<ProductTO> response = new Response<>();
		try {
			response.setAttachedObject(this.findProductByCode(code));
		} catch (ProductNotFoundException e) {
			log.error("Product Not Found\n" + e);
			response.setException(e);
			response.setMessage("Product Not Found");
		}
		return response;
	}

	@Override
	public Response<List<ProductTO>> findAllProductsInJson() {
		Response<List<ProductTO>> response = new Response<>();
		response.setAttachedObject(employeeDao.findAll().stream().map(emp -> modelMapper.map(emp, ProductTO.class))
				.collect(Collectors.toList()));
		log.info("Retrived all products");
		return response;
	}

	@Override
	public String findAllProductsInCSV() {
		List<ProductTO> allProducts = employeeDao.findAll().stream().map(emp -> modelMapper.map(emp, ProductTO.class))
				.collect(Collectors.toList());
		log.info("List of products are downloaded as csv file");
		return convertToCSV(allProducts);
	}

	@Override
	public Response<ProductTO> deleteAllProducts() {
		Response<ProductTO> response = new Response<>();
		employeeDao.deleteAll();
		response.setMessage("All Products deleted successfully");
		log.info("All Products deleted successfully");
		return response;
	}

	private Function<String, ProductTO> mapToProduct = (line) -> {
		String[] p = line.split(",");
		ProductTO product = new ProductTO();
		product.setSource(p[0].substring(1, p[0].length() - 1));
		product.setCodeListCode(p[1].substring(1, p[1].length() - 1));
		product.setCode(p[2].substring(1, p[2].length() - 1));
		product.setDisplayValue(p[3].substring(1, p[3].length() - 1));
		product.setLongDescription(p[4].substring(1, p[4].length() - 1));
		product.setFromDate(p[5].substring(1, p[5].length() - 1));
		product.setToDate(p[6].substring(1, p[6].length() - 1));
		product.setSortingPriority(p[7].substring(1, p[7].length() - 1));
		return product;
	};

	private String convertToCSV(List<ProductTO> allProducts) {
		String HEADER = "\"source\",\"codeListCode\",\"code\",\"displayValue\",\"longDescription\",\"fromDate\",\"toDate\",\"sortingPriority\"\n";
		List<String> productList = new ArrayList<>();
		productList.add(HEADER);
		return HEADER.concat(allProducts.stream().map(ProductTO::toString).collect(Collectors.joining("\n")));
	}

	private ProductTO findProductByCode(final String code) {
		return modelMapper.map(
				employeeDao.findById(code).orElseThrow(() -> new ProductNotFoundException("Product Not Found")),
				ProductTO.class);
	}
}
