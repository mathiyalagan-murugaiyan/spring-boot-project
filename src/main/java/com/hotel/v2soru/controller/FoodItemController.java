package com.hotel.v2soru.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.v2soru.configure.ResponseStructure;
import com.hotel.v2soru.entity.FoodItem;
import com.hotel.v2soru.service.FoodItemService;

@RestController
@RequestMapping("foodItem")
public class FoodItemController {
	
	@Autowired
	private FoodItemService foodItemService;
	
	@GetMapping("find")
	public ResponseEntity<ResponseStructure<FoodItem>> findFoodItem(@RequestParam long foodItemId){
		
		return foodItemService.findFoodItem(foodItemId);
	}
	
	@GetMapping("findAll")
	public ResponseEntity<ResponseStructure<List<FoodItem>>> findAllFoodItem(){
		
		return foodItemService.findAllFoodItem();
	}
	
	@PostMapping("save")
	public ResponseEntity<ResponseStructure<FoodItem>> saveFoodItem(@RequestBody FoodItem foodItem){
		
		return foodItemService.saveFoodItem(foodItem);
	}
	
	@PutMapping("update")
	public ResponseEntity<ResponseStructure<FoodItem>> updateFoodItem(@RequestParam long foodItemId,@RequestBody FoodItem foodItem){
		
		return foodItemService.updateFoodItem(foodItemId, foodItem);
	}
	
	
	@DeleteMapping("delete")
	public ResponseEntity<ResponseStructure<FoodItem>> dleteFoodItem(@RequestParam long foodItemId){
		
		return foodItemService.deleteFoodItem(foodItemId);
	}

}
