package com.gerimedica.codingTest.to;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@Data
public class ProductTO {

	/**
	 * 
	 */

	@CsvBindByName(column = "source")
	private String source;
	@CsvBindByName(column = "codeListCode")
	private String codeListCode;
	@CsvBindByName(column = "code")
	private String code;
	@CsvBindByName(column = "displayValue")
	private String displayValue;
	@CsvBindByName(column = "longDescription")
	private String longDescription;
	@CsvBindByName(column = "fromDate")
	private String fromDate;
	@CsvBindByName(column = "toDate")
	private String toDate;
	@CsvBindByName(column = "sortingPriority")
	private String sortingPriority;

	public ProductTO(String source, String codeListCode, String code, String displayValue, String longDescription,
			String fromDate, String toDate, String sortingPriority) {
		super();
		this.codeListCode = codeListCode;
		this.code = code;
		this.displayValue = displayValue;
		this.longDescription = longDescription;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.sortingPriority = sortingPriority;
	}
	public ProductTO() {}
}
