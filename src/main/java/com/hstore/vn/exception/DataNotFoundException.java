package com.hstore.vn.exception;

public class DataNotFoundException  extends RuntimeException{
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

	public DataNotFoundException(String message) {
		this.message = message;
	}

}
