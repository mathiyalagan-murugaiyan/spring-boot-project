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
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long adminId;

	private String adminName;

	private String adminEmail;

	private String adminPassword;
    @OneToMany(cascade = CascadeType.ALL)
	private List<User> managedUsers; // One-to-Many ->one admin many user
    @OneToMany(cascade = CascadeType.ALL)
	private List<Order> managedOrders; // One-to-Many ->one admin many orders
    @OneToMany
	private List<DeliveryBoy> managedDeliveryBoys;
}
