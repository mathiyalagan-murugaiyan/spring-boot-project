package com.hotel.v2soru.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class DeliveryBoy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long deliveryBoyId;
    
    private String deliveryBoyName;
    
    private String phoneNumber;
    
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<FoodOrder> foodOrders;
    
   
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
}	

