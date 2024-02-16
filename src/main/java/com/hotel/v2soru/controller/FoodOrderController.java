
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
import com.hotel.v2soru.entity.FoodOrder;
import com.hotel.v2soru.service.FoodOrderService;

@RestController
@RequestMapping("foodOrder")
public class FoodOrderController {
	
	@Autowired
	private FoodOrderService foodOrderService;
	
	@GetMapping("find")
	public ResponseEntity<ResponseStructure<FoodOrder>> findFoodOrder(@RequestParam long foodOrderId){
		
		return foodOrderService.findFoodOrder(foodOrderId);
	}
	
	@GetMapping("findAll")
	public ResponseEntity<ResponseStructure<List<FoodOrder>>> findAllFoodOrder(){
		
		return foodOrderService.findAllFoodOrder();
	}
	
	@PostMapping("save")
	public ResponseEntity<ResponseStructure<FoodOrder>> saveFoodOrder(@RequestBody FoodOrder foodOrder){
		
		return foodOrderService.saveFoodOrder(foodOrder);
	}
	
	@PutMapping("update")
	public ResponseEntity<ResponseStructure<FoodOrder>> updateFoodOrder(@RequestParam long foodOrderId,@RequestBody FoodOrder foodOrder){
		
		return foodOrderService.updateFoodOrder(foodOrderId, foodOrder);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<ResponseStructure<FoodOrder>> deleteFoodOrder(@RequestParam long foodOrderId){
		
		return foodOrderService.deleteFoodOrder(foodOrderId);
	}
	
	@PutMapping("assignItem")
	public ResponseEntity<ResponseStructure<FoodOrder>> aasignFoodItem(@RequestParam long foodOrderId,@RequestParam long foodItemId){
		
		return foodOrderService.aasignFoodItem(foodOrderId, foodItemId);
	}
	
	@PutMapping("removeItem")
	 public ResponseEntity<ResponseStructure<FoodOrder>> removeFoodItem(@RequestParam long foodOrderId,@RequestParam long foodItemId){
		 
		 return foodOrderService.removeFoodItem(foodOrderId, foodItemId);
	 }

}
