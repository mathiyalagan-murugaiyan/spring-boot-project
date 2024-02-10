package com.hotel.v2soru.dao;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hotel.v2soru.entity.FoodItem;
import com.hotel.v2soru.repository.FoodItemRepo;

@Repository
public class FoodItemDao {
	
	@Autowired
	private FoodItemRepo foodItemRepo;
	
	public FoodItem findFoodItem(long foodItemId) {
		
		Optional<FoodItem> foodItem = foodItemRepo.findById(foodItemId);
		if(foodItem.isPresent()) {
		return	foodItemRepo.findById(foodItemId).get();
		}
		return null;
	}
	
	public List<FoodItem> findAllFoodItem() {
		
		return foodItemRepo.findAll();
	}
	
	public FoodItem saveFoodItem(FoodItem foodItem) {
		
		return foodItemRepo.save(foodItem);
		
	}
	
	public FoodItem updateFoodItem(long foodId , FoodItem foodItem) {
		
		FoodItem exfoodItem = findFoodItem(foodId);
		if(exfoodItem.getItemId() == foodId) {
		ModelMapper mapper = new ModelMapper();
		mapper.map(foodItem , exfoodItem);
		foodItemRepo.save(exfoodItem);
		return exfoodItem;
		}
		return null;
	}
	
	public FoodItem deleteFoodItem(long foodId) {
		FoodItem fooditem = findFoodItem(foodId);
		
		if(fooditem.getItemId() == foodId) {
			foodItemRepo.deleteById(foodId);
			return fooditem;
		}
		return null;
	}

}
