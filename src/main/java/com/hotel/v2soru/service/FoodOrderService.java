package com.hotel.v2soru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hotel.v2soru.configure.ResponseStructure;
import com.hotel.v2soru.dao.FoodOrderDao;
import com.hotel.v2soru.entity.FoodOrder;

@Service
public class FoodOrderService {
	
	@Autowired
	private FoodOrderDao foodOrderDao;
	
 public ResponseEntity<ResponseStructure<FoodOrder>> findFoodOrder(long foodOrderId){
	 
	 FoodOrder foodOrder = foodOrderDao.findFoodOrder(foodOrderId);
	 if(foodOrder != null) {
		 ResponseStructure<FoodOrder> structure = new ResponseStructure<FoodOrder>();
		 structure.setMessage("Food Order Found");
		 structure.setStatusCode(HttpStatus.FOUND.value());
		 structure.setData(foodOrderDao.findFoodOrder(foodOrderId));
		 
		 return new ResponseEntity<ResponseStructure<FoodOrder>>(structure,HttpStatus.FOUND);
	 }
	 return null; // throw food order does not exist
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
	 
	 return null; // throw foodorder list not found
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
	 
	 return null;// throw food order does not exist
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

	 return null; //food order does not exist
 }

}
