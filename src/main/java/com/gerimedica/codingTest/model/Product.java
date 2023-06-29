package com.gerimedica.codingTest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	@Id
	private String code;
	@Column
	private String source;
	@Column
	private String codeListCode;
	@Column
	private String displayValue;
	@Column
	private String longDescription;
	@Column
	private String fromDate;
	@Column
	private String toDate;
	@Column
	private String sortingPriority;
}
