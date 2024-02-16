package com.hotel.v2soru.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hotel.v2soru.configure.ResponseStructure;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	// validation Exception
	
	@ExceptionHandler
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex){
		ResponseStructure<Object> structure = new ResponseStructure<Object>();
		Map<String,String> hashmap = new HashMap<String, String>();
		
		for(ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			String field = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			hashmap.put(field, message);
		}
		structure.setMessage("add proper details");
		structure.setStatusCode(HttpStatus.FORBIDDEN.value());
		structure.setData(hashmap);
		return new ResponseEntity<Object>(structure,HttpStatus.BAD_REQUEST);
	}
	
	//User Exception
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> userNotFoundException(UserNotFoundException ex){
		
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("User Not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> userListNotFoundException(UserListNotFoundException ex){
		
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("User List Not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	//Admin Exceptions 
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> adminNotFound(AdminNotFoundException ex){
		
		ResponseStructure<String> structure =new  ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("Admin Not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> adminListNotFound(AdminListNotFoundException ex){
		
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("Admin List Not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	//FoodOrder Exception
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> foodOrderNotFound(FoodOrderNotFound ex){
		
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("FoodOrder Not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> foodOrderListNotFound(FoodOrderListNotFound ex){
		 
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("FoodOrder List Not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
	}
	
	//FoodItem Exception
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> foodItemNotFound(FoodItemNotFound ex){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("FoodItem Not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> foodItemListNotFound(FoodItemListNotFound ex) {
		
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("FoodItem List Not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	//DeliveryBoy Exception
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> deliveryBoyNotFound(DeliveryBoyNotFound ex){
		
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("DeliveryBoy Not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> deliveryBoyListNotFound(DeliveryBoyListNotFound ex){
		
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("DeliveryBoy List Not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	
	}
}

 