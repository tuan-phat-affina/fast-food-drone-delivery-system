package com.fast_food_drone_delivery_system.repository;

import com.fast_food_drone_delivery_system.entity.Drone;
import com.fast_food_drone_delivery_system.enums.DroneStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, Long> {
    List<Drone> findByStatus(DroneStatus status);
}
