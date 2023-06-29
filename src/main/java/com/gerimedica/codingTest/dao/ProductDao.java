package com.gerimedica.codingTest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerimedica.codingTest.model.Product;

public interface ProductDao extends JpaRepository<Product, String>{

	
}
