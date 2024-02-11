package com.hotel.v2soru.exception;

@SuppressWarnings("serial")
public class FoodItemListNotFound extends RuntimeException{
	
	private String message;

	public String getMessage() {
		return message;
	}

	public FoodItemListNotFound(String message) {
		super();
		this.message = message;
	}

}
