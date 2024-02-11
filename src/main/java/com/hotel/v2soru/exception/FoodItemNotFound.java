package com.hotel.v2soru.exception;

@SuppressWarnings("serial")
public class FoodItemNotFound extends RuntimeException{
	
	private String message;

	public String getMessage() {
		return message;
	}

	public FoodItemNotFound(String message) {
		super();
		this.message = message;
	}

}
