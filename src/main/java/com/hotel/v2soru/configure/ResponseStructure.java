package com.hotel.v2soru.configure;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	
	private String message;
	
	private int statusCode;
	
	private T data;

}
