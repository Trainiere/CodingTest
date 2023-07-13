package com.gerimedica.codingTest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.gerimedica.codingTest.dao.ProductDao;
import com.gerimedica.codingTest.model.Product;
import com.gerimedica.codingTest.to.ProductTO;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@Mock
	private ProductDao productDao;
	
	@Autowired
	@InjectMocks
	private ProductServiceImpl productServiceImpl;
	private Product product1;
    private Product product2;
    List<Product> productList;
    private ProductTO productTO1;
    private ProductTO productTO2;
    List<ProductTO> productTOList;
	
	@BeforeEach
	public void setup() {
		product1 = new Product("CodeTest","SourceTest","CodeListCodeTest","displayValueTest","lognDescriptionTest","fromDateTest","toDateTest","sortingPriorityTest");
		product1 = new Product("CodeTest2","SourceTest2","CodeListCodeTest2","displayValueTest2","lognDescriptionTest2","fromDateTest2","toDateTest2","sortingPriorityTest2");
		productList.add(product1);
		productList.add(product2);
		
		productTO1 = new ProductTO("CodeTest","SourceTest","CodeListCodeTest","displayValueTest","lognDescriptionTest","fromDateTest","toDateTest","sortingPriorityTest");
		productTO1 = new ProductTO("CodeTest2","SourceTest2","CodeListCodeTest2","displayValueTest2","lognDescriptionTest2","fromDateTest2","toDateTest2","sortingPriorityTest2");
		productTOList.add(productTO1);
		productTOList.add(productTO2);
	}
	
	@AfterEach
	public void deleteAll() {
		product1 = null;
		product2 = null;
		productList = null;
	}
	
	@Test
	public void givenProductToAddShouldReturnAddedProduct() {
		when(productDao.findAll()).thenReturn(productList);
		List<ProductTO> products = productServiceImpl.findAllProductsInJson();
		assertEquals(productTOList, products);
		
	}
}
