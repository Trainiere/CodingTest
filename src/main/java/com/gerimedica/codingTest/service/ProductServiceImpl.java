package com.gerimedica.codingTest.service;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gerimedica.codingTest.controller.ProductController;
import com.gerimedica.codingTest.dao.ProductDao;
import com.gerimedica.codingTest.exception.ProductException;
import com.gerimedica.codingTest.exception.ProductNotFoundException;
import com.gerimedica.codingTest.to.ProductTO;
import com.gerimedica.codingTest.to.Response;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.MappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@Service
public class ProductServiceImpl implements ProductService {

	Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void createAllproduct(MultipartFile file) {
		if (file == null || !file.getOriginalFilename().endsWith(".csv")) {
			throw new ProductException("File is not a csv file");
		}
		if (file == null || file.getSize() == 0) {
			throw new ProductException("Empty File is uploaded");
		}
		try (Reader reader = new InputStreamReader(file.getInputStream())) {
			List<ProductTO> products = new CsvToBeanBuilder<ProductTO>(reader).withType(ProductTO.class)
					.withIgnoreLeadingWhiteSpace(true).withIgnoreEmptyLine(true).build().parse();
			productDao.saveAll(products.stream().map(product -> modelMapper.map(product, Product.class))
					.collect(Collectors.toList()));

		} catch (Exception e) {
			throw new ProductException("Error occured while reading csv file");
		}

	}

	@Override
	public ProductTO findProductWithCode(String code) {
		return modelMapper.map(
				productDao.findById(code).orElseThrow(() -> new ProductNotFoundException("Product Not Found")),
				ProductTO.class);
	}

	@Override
	public List<ProductTO> findAllProductsInJson() {
		List<ProductTO> products = productDao.findAll().stream().map(emp -> modelMapper.map(emp, ProductTO.class))
				.collect(Collectors.toList());
		log.info("Retrived all products");
		return products;
	}

	@Override
	public StringWriter findAllProductsInCSV() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException{
		List<ProductTO> allProducts = productDao.findAll().stream().map(emp -> modelMapper.map(emp, ProductTO.class))
				.collect(Collectors.toList());
		StringWriter stringWriter = new StringWriter();
			MappingStrategy<ProductTO> columnStrategy = new HeaderColumnNameMappingStrategy<ProductTO>();
			columnStrategy.setType(ProductTO.class);
			StatefulBeanToCsv<ProductTO> csvBuilder = new StatefulBeanToCsvBuilder<ProductTO>(stringWriter)
					.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER)
					.withMappingStrategy(columnStrategy).build();
			csvBuilder.write(allProducts);
		return stringWriter;
	}

	@Override
	public void deleteAllProducts() {
		productDao.deleteAll();
		log.info("All Products deleted successfully");
	}

}
