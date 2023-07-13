package com.gerimedica.codingTest.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gerimedica.codingTest.model.Product;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProductDaoTest {
	@Autowired
	private ProductDao productDao;
	Product product;
	@BeforeEach
	public void startup() {
		product = new Product("CodeTest","SourceTest","CodeListCodeTest","displayValueTest","lognDescriptionTest","fromDateTest","toDateTest","sortingPriorityTest"); 
	}
	
	@AfterEach
	private void eraseAll() {
		productDao.deleteAll();
		product = null;
	}
	
	@Test
	public void givenProductToAddShouldReturnSingleProductOnFetch() {
		productDao.save(product);
		Product fetchProduct = productDao.findById(product.getCode()).get();
		assertEquals("CodeTest", fetchProduct.getCode());
	}
}
