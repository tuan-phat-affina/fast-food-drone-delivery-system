package com.fast_food_drone_delivery_system.repository;

import com.fast_food_drone_delivery_system.entity.Dish;
import com.fast_food_drone_delivery_system.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, JpaSpecificationExecutor<Restaurant> {
}
