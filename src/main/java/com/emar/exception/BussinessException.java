package com.emar.exception;

public class BussinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public BussinessException () {
		super();
	}
	
	public BussinessException (String message) {
		super(message);
	}
	
	public BussinessException (String message, Throwable cause) {
		super(message, cause);
	}
}
