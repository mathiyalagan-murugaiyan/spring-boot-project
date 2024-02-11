package com.hotel.v2soru.exception;

@SuppressWarnings("serial")
public class DeliveryBoyNotFound extends RuntimeException{
	
	private String message;

	public String getMessage() {
		return message;
	}

	public DeliveryBoyNotFound(String message) {
		super();
		this.message = message;
	}
}
