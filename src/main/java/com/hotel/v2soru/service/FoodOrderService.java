package com.hotel.v2soru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hotel.v2soru.configure.ResponseStructure;
import com.hotel.v2soru.dao.FoodItemDao;
import com.hotel.v2soru.dao.FoodOrderDao;
import com.hotel.v2soru.entity.FoodItem;
import com.hotel.v2soru.entity.FoodOrder;
import com.hotel.v2soru.exception.FoodItemNotFound;
import com.hotel.v2soru.exception.FoodOrderListNotFound;
import com.hotel.v2soru.exception.FoodOrderNotFound;

@Service
public class FoodOrderService {
	
	@Autowired
	private FoodOrderDao foodOrderDao;
	
	@Autowired
	private FoodItemDao foodItemDao;
	
	
 public ResponseEntity<ResponseStructure<FoodOrder>> findFoodOrder(long foodOrderId){
	 
	 FoodOrder foodOrder = foodOrderDao.findFoodOrder(foodOrderId);
	 if(foodOrder != null) {
		 ResponseStructure<FoodOrder> structure = new ResponseStructure<FoodOrder>();
		 structure.setMessage("Food Order Found");
		 structure.setStatusCode(HttpStatus.FOUND.value());
		 structure.setData(foodOrderDao.findFoodOrder(foodOrderId));
		 
		 return new ResponseEntity<ResponseStructure<FoodOrder>>(structure,HttpStatus.FOUND);
	 }
	 throw new FoodOrderNotFound("FoodOrder does not exist");
 }
 
 public ResponseEntity<ResponseStructure<List<FoodOrder>>> findAllFoodOrder(){
	 List<FoodOrder> exfoodOrder = foodOrderDao.findAllFoodOrder();
	 if(!exfoodOrder.isEmpty()) {
		 ResponseStructure<List<FoodOrder>> structure = new ResponseStructure<List<FoodOrder>>();
		 structure.setMessage("FoodOrder List Found");
		 structure.setStatusCode(HttpStatus.FOUND.value());
		 structure.setData(foodOrderDao.findAllFoodOrder());
		 
		 return new ResponseEntity<ResponseStructure<List<FoodOrder>>>(structure,HttpStatus.FOUND);
	 }
	 
	 throw new FoodOrderListNotFound("FoodOrder List does not exist");
 }
 
 public ResponseEntity<ResponseStructure<FoodOrder>> saveFoodOrder(FoodOrder foodOrder){
	 
	 ResponseStructure<FoodOrder> structure = new ResponseStructure<FoodOrder>();
	 structure.setMessage("FoodOrder saved");
	 structure.setStatusCode(HttpStatus.CREATED.value());
	 structure.setData(foodOrderDao.saveFoodOrder(foodOrder));
	 
	 return new ResponseEntity<ResponseStructure<FoodOrder>>(structure,HttpStatus.CREATED);
 }
 
 public ResponseEntity<ResponseStructure<FoodOrder>> updateFoodOrder(long foodOrderId,FoodOrder foodOrder){
	 
	 FoodOrder exfoodOrder = foodOrderDao.findFoodOrder(foodOrderId);
	 if(exfoodOrder != null) {
		 ResponseStructure<FoodOrder> structure = new ResponseStructure<FoodOrder>();
		 structure.setMessage("Food Order Updated");
		 structure.setStatusCode(HttpStatus.OK.value());
		 structure.setData(foodOrderDao.updateFoodOrder(foodOrderId, foodOrder));
		 
		 return new ResponseEntity<ResponseStructure<FoodOrder>>(structure,HttpStatus.OK);
	 }
	 
	 throw new FoodOrderNotFound("FoodOrder does not exist");
 }
 
 public ResponseEntity<ResponseStructure<FoodOrder>> deleteFoodOrder(long foodOrderId){
	 
	 FoodOrder foodOrder = foodOrderDao.findFoodOrder(foodOrderId);
	 if(foodOrder != null) {
		 
		 ResponseStructure<FoodOrder> structure = new ResponseStructure<FoodOrder>();
		 structure.setMessage("Food Order Deleted");
		 structure.setStatusCode(HttpStatus.OK.value());
		 structure.setData(foodOrderDao.deleteFoodOrder(foodOrderId));
		 
		 return new ResponseEntity<ResponseStructure<FoodOrder>>(structure,HttpStatus.OK);
	 }

	 throw new FoodOrderNotFound("FoodOrder does not exist");
 }
 
 public ResponseEntity<ResponseStructure<FoodOrder>> aasignFoodItem(long foodOrderId, long foodItemId){
	    
	 FoodOrder foodOrder = foodOrderDao.findFoodOrder(foodOrderId);
	 FoodItem foodItem = foodItemDao.findFoodItem(foodItemId);
	
	 if(foodOrder != null && foodItem != null) {
		 List<FoodItem> items = foodOrder.getItems();
				items.add(foodItem);
		 
		long totalCost = calculateTotalCost(items);
		 
		foodOrder.setTotalCost(totalCost);
		foodOrder.setItems(items);
		 FoodOrder updateFoodOrder = foodOrderDao.updateFoodOrder(foodOrderId, foodOrder);
		 
		 ResponseStructure<FoodOrder> structure = new ResponseStructure<FoodOrder>();
		 structure.setMessage("foodItem assigned");
		 structure.setStatusCode(HttpStatus.OK.value());
		 structure.setData(updateFoodOrder);
		 return new ResponseEntity<ResponseStructure<FoodOrder>>(structure,HttpStatus.OK);
	 }
	 throw new FoodItemNotFound("Food Item does not exist");
 }
 private long calculateTotalCost(List<FoodItem> items) {
	    long totalCost = 0;
	    for (FoodItem item : items) {
	        totalCost += (item.getCostPerItem()* item.getItemQuentity());
	    }
	    return totalCost;
	}
 

 public ResponseEntity<ResponseStructure<FoodOrder>> removeFoodItem(long foodOrderId, long foodItemId){
	    
	 FoodOrder foodOrder = foodOrderDao.findFoodOrder(foodOrderId);
	 FoodItem foodItem = foodItemDao.findFoodItem(foodItemId);
	
	 if(foodOrder != null && foodItem != null) {
		 List<FoodItem> items = foodOrder.getItems();
	             items.remove(foodItem);
	             foodOrder.setItems(items);
		
		 long totalCost = reduceTotalCost(items);
		 
		 foodOrder.setTotalCost(totalCost);
		 FoodOrder updateFoodOrder = foodOrderDao.saveFoodOrder(foodOrder);
		 
		 ResponseStructure<FoodOrder> structure = new ResponseStructure<FoodOrder>();
		 structure.setMessage("foodItem removed");
		 structure.setStatusCode(HttpStatus.OK.value());
		 structure.setData(updateFoodOrder);
		 return new ResponseEntity<ResponseStructure<FoodOrder>>(structure,HttpStatus.OK);
	
	 }
	 throw new FoodItemNotFound("Food Item does not exist");
 }
 private long reduceTotalCost(List<FoodItem> items) {
	    long totalCost = 0;
	    for (FoodItem item : items) {
	        totalCost += item.getCostPerItem();
	    }
	    return totalCost;
	}
 


}
