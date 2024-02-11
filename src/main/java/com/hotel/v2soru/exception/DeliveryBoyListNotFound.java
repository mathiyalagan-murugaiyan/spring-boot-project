package com.hotel.v2soru.exception;

@SuppressWarnings("serial")
public class DeliveryBoyListNotFound extends RuntimeException{
	
	private String message;

	public String getMessage() {
		return message;
	}

	public DeliveryBoyListNotFound(String message) {
		super();
		this.message = message;
	}
}
