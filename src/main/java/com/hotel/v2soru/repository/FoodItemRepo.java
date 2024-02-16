package com.hotel.v2soru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hotel.v2soru.entity.FoodItem;

public interface FoodItemRepo extends JpaRepository<FoodItem, Long> {
	
	
	@Query("SELECT u FROM User u WHERE u.userEmail = :foodName")
	FoodItem findByfoodName(String foodName);
	
}
