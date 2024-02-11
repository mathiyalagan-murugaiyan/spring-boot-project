package com.hotel.v2soru.exception;

@SuppressWarnings("serial")
public class AdminListNotFoundException extends RuntimeException{
	
	private String message;

	public String getMessage() {
		return message;
	}

	public AdminListNotFoundException(String message) {
		super();
		this.message = message;
	}

}
