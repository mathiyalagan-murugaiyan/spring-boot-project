package com.hotel.v2soru.exception;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException{
	
	private String message;

	public String getMessage() {
		return message;
	}

	public UserNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	

}
