package com.gerimedica.codingTest.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gerimedica.codingTest.to.ProductTO;
import com.gerimedica.codingTest.to.Response;

@Service
public interface ProductService {
	Response<ProductTO> createAllproduct(MultipartFile file);

	Response<ProductTO> findProductWithCode(String code);

	Response<List<ProductTO>> findAllProductsInJson();

	String findAllProductsInCSV();

	Response<ProductTO> deleteAllProducts();

}
