package com.gerimedica.codingTest.to;

import java.io.Serializable;

public class ProductTO implements Serializable, Cloneable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	private String source;
	private String codeListCode;
	private String code;
	private String displayValue;
	private String longDescription;
	private String fromDate;
	private String toDate;
	private String sortingPriority;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCodeListCode() {
		return codeListCode;
	}

	public void setCodeListCode(String codeListCode) {
		this.codeListCode = codeListCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getSortingPriority() {
		return sortingPriority;
	}

	public void setSortingPriority(String sortingPriority) {
		this.sortingPriority = sortingPriority;
	}

	@Override
	public String toString() {
		return "\"" + source + "\"," + "\"" + codeListCode + "\"," + "\"" + code + "\"," + "\"" + displayValue + "\","
				+ "\"" + longDescription + "\"," + "\"" + fromDate + "\"," + "\"" + toDate + "\"," + "\""
				+ sortingPriority + "\"";
	}

	public ProductTO(String source, String codeListCode, String code, String displayValue, String longDescription,
			String fromDate, String toDate, String sortingPriority) {
		super();
		this.source = source;
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
