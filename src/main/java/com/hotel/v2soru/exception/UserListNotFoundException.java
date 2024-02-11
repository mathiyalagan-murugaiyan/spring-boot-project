package com.hotel.v2soru.exception;

@SuppressWarnings("serial")
public class UserListNotFoundException extends RuntimeException{
	
	private String message;

	public String getMessage() {
		return message;
	}

	public UserListNotFoundException(String message) {
		super();
		this.message = message;
	}

}
