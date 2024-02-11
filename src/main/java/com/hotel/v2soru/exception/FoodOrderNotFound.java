package com.hotel.v2soru.exception;

@SuppressWarnings("serial")
public class FoodOrderNotFound extends RuntimeException{
	
	private String message;

	public String getMessage() {
		return message;
	}

	public FoodOrderNotFound(String message) {
		super();
		this.message = message;
	}

}
