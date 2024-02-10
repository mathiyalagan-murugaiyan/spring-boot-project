package com.hotel.v2soru.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.v2soru.entity.FoodOrder;

public interface FoodOrderRepo  extends JpaRepository<FoodOrder, Long>{

}
