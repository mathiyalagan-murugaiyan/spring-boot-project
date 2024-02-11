package com.hotel.v2soru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hotel.v2soru.configure.ResponseStructure;
import com.hotel.v2soru.dao.FoodItemDao;
import com.hotel.v2soru.entity.FoodItem;

@Service
public class FoodItemService {
	
	@Autowired
	private FoodItemDao foodItemDao;
	
	public ResponseEntity<ResponseStructure<FoodItem>> findFoodItem(long foodItem){
		
		FoodItem exfoodItem = foodItemDao.findFoodItem(foodItem);
		if(exfoodItem != null) {
			ResponseStructure<FoodItem> structure = new ResponseStructure<FoodItem>();
			structure.setMessage("Food Item Found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setData(foodItemDao.findFoodItem(foodItem));
			
			return new ResponseEntity<ResponseStructure<FoodItem>>(structure,HttpStatus.FOUND);
		}
		
		return null; //throw food item does not exist
	}
	
	public ResponseEntity<ResponseStructure<List<FoodItem>>> findAllFoodItem(){
		
		List<FoodItem> allFoodItem = foodItemDao.findAllFoodItem();
		if(!allFoodItem.isEmpty()) {
			
			ResponseStructure<List<FoodItem>> structure = new ResponseStructure<List<FoodItem>>();
			structure.setMessage("FoodItem List Found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setData(foodItemDao.findAllFoodItem());
			
			return new ResponseEntity<ResponseStructure<List<FoodItem>>>(structure,HttpStatus.FOUND);
		}
		
		return null; // throw fooditem list not found
	}
	
	public ResponseEntity<ResponseStructure<FoodItem>> saveFoodItem(FoodItem foodItem){
		
		ResponseStructure<FoodItem> structure = new ResponseStructure<FoodItem>();
		structure.setMessage("FoodItem saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setData(foodItemDao.saveFoodItem(foodItem));
		
		return new ResponseEntity<ResponseStructure<FoodItem>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<FoodItem>> updateFoodItem(long foodItemId,FoodItem foodItem){
		 FoodItem exfoodItem = foodItemDao.findFoodItem(foodItemId);
		 if(exfoodItem != null) {
			 ResponseStructure<FoodItem> structure = new ResponseStructure<FoodItem>();
			 structure.setMessage("FoodItem Updated");
			 structure.setStatusCode(HttpStatus.OK.value());
			 structure.setData(foodItemDao.updateFoodItem(foodItemId, foodItem));
			 
			 return new ResponseEntity<ResponseStructure<FoodItem>>(structure,HttpStatus.OK);
			 
		 }
		
		return null; //throw food item does not exist
	}
	
	public ResponseEntity<ResponseStructure<FoodItem>> deleteFoodItem(long foodItemId){
		FoodItem foodItem = foodItemDao.findFoodItem(foodItemId);
		if(foodItem != null) {
			ResponseStructure<FoodItem> structure = new ResponseStructure<FoodItem>();
			structure.setMessage("FoodItem deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData(foodItemDao.deleteFoodItem(foodItemId));
			
			return new ResponseEntity<ResponseStructure<FoodItem>>(structure,HttpStatus.OK);
		}
		return null; // throw fooditem does not exist
	}

}
