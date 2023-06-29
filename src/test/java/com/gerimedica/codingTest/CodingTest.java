package com.gerimedica.codingTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import com.gerimedica.codingTest.service.ProductService;
import com.gerimedica.codingTest.to.ProductTO;
import com.gerimedica.codingTest.to.Response;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class CodingTest {

	public CodingTest() {

	}

	private boolean setUpOk = false;
	@Autowired
	private ProductService productService;

	@Before
	public void setUp() {
		if (setUpOk) {
			return;
		}
		try {
			File resource = new ClassPathResource("exercise.csv").getFile();
			FileInputStream input = new FileInputStream(resource);
			MultipartFile multipartFile = new MockMultipartFile("file", resource.getName(), "application/csv", input);
			productService.createAllproduct(multipartFile);
		} catch (IOException i) {

		}
		setUpOk = true;
	}

	@Test
	public void givenAllProducts_getProductWithCode_thenMatch() throws Exception {
		Assert.assertEquals("The long description is necessary",
				productService.findProductWithCode("271636001").getAttachedObject().getLongDescription());
		;
	}

	@Test
	public void givenAllProducts_thenMatchResponse() throws Exception {
		productService.deleteAllProducts();
		File resource = new ClassPathResource("exercise.csv").getFile();
		FileInputStream input = new FileInputStream(resource);
		MultipartFile multipartFile = new MockMultipartFile("file", resource.getName(), "application/csv", input);
		Assert.assertEquals("All products created successfully",
				productService.createAllproduct(multipartFile).getMessage());
		setUpOk = false;
	}

	@Test
	public void givenAllProducts_deleteAllProduct_thenMatch() throws Exception {
		productService.deleteAllProducts();
		setUpOk = false;
		Response<ProductTO> product = productService.findProductWithCode("271636001");
		Assert.assertEquals(null, product.getAttachedObject());
		Assert.assertEquals("Product Not Found", product.getMessage());
		;
	}

	@Test
	public void convertlistToCSVformat() {
		String resultText = "\"source\",\"codeListCode\",\"code\",\"displayValue\",\"longDescription\",\"fromDate\",\"toDate\",\"sortingPriority\"\n"
				+ "\"ZIB\",\"ZIB001\",\"271636001\",\"Polsslag regelmatig\",\"The long description is necessary\",\"01-01-2019\",\"\",\"1\"";
		List<ProductTO> list = Arrays.asList(new ProductTO("ZIB", "ZIB001", "271636001", "Polsslag regelmatig",
				"The long description is necessary", "01-01-2019", "", "1"));
		Assert.assertEquals(resultText, this.convertToCSV(list));
	}

	private String convertToCSV(List<ProductTO> allProducts) {
		String HEADER = "\"source\",\"codeListCode\",\"code\",\"displayValue\",\"longDescription\",\"fromDate\",\"toDate\",\"sortingPriority\"\n";
		List<String> productList = new ArrayList<>();
		productList.add(HEADER);
		return HEADER.concat(allProducts.stream().map(ProductTO::toString).collect(Collectors.joining("\n")));
	}

}
