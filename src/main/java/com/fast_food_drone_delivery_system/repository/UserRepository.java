package com.fast_food_drone_delivery_system.repository;

import com.fast_food_drone_delivery_system.entity.Restaurant;
import com.fast_food_drone_delivery_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
}
