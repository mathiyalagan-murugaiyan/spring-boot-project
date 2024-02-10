package com.hotel.v2soru.dao;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hotel.v2soru.entity.FoodOrder;
import com.hotel.v2soru.repository.FoodOrderRepo;

@Repository
public class FoodOrderDao {
	
	@Autowired
	private FoodOrderRepo foodOrderRepo;
	
	public FoodOrder findFoodOrder(long foodOrderId) {
		
		Optional<FoodOrder> foodorder = foodOrderRepo.findById(foodOrderId);
		if(foodorder.isPresent()) {
			return foodOrderRepo.findById(foodOrderId).get();
		}
		return null;
	}
	
	public List<FoodOrder> findAllFoodOrder() {
		
		return foodOrderRepo.findAll();
	}
	
	public FoodOrder saveFoodOrder(FoodOrder foodOrder) {
		
		return foodOrderRepo.save(foodOrder);
		
	}
	
	public FoodOrder updateFoodOrder(long foodOrderId, FoodOrder foodOrder) {
		FoodOrder exFoodOrder = findFoodOrder(foodOrderId);
		if(exFoodOrder.getFoodOrderId() == foodOrderId) {
		ModelMapper mapper = new ModelMapper();
		mapper.map(foodOrder , exFoodOrder);
		
		return exFoodOrder;
		}
		return null;
	}
	
	public FoodOrder deleteFoodOrder(long foodOrderId) {
		
		FoodOrder foodOrder = findFoodOrder(foodOrderId);
		if(foodOrder.getFoodOrderId() == foodOrderId) {
			foodOrderRepo.deleteById(foodOrderId);
			return foodOrder;
		}
		return null;
		
	}

}
