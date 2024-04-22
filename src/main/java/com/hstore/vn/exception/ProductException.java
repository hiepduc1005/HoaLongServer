package com.hstore.vn.exception;

public class ProductException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ProductException(String message) {
		this.message = message;
	}

	
}
