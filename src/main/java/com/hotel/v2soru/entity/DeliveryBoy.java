package com.hotel.v2soru.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class DeliveryBoy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deliveryBoyId;
	private String deliveryBoyname;
	private String phoneNumber;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Order> orders;

}
