package com.gerimedica.codingTest.to;

import java.io.Serializable;

public class Response<T> implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private T attachedObject;
	private Exception exception;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getAttachedObject() {
		return attachedObject;
	}

	public void setAttachedObject(T attachedObject) {
		this.attachedObject = attachedObject;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

}
