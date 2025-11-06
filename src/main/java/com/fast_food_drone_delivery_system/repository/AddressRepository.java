package com.fast_food_drone_delivery_system.repository;

import com.fast_food_drone_delivery_system.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
