package com.hotel.v2soru.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class FoodItem {
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long itemId;
	private String name;
	private double price;

}
