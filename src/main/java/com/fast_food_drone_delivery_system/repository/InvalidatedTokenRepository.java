package com.fast_food_drone_delivery_system.repository;

import com.fast_food_drone_delivery_system.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidatedTokenRepository  extends JpaRepository<InvalidatedToken, String> {
}
