package com.hotel.v2soru.exception;

@SuppressWarnings("serial")
public class AdminNotFoundException extends RuntimeException{
	
	private String message;

	public String getMessage() {
		return message;
	}

	public AdminNotFoundException(String message) {
		super();
		this.message = message;
	}

}
