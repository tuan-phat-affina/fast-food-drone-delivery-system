package com.fast_food_drone_delivery_system.repository;

import com.fast_food_drone_delivery_system.entity.Drone;
import com.fast_food_drone_delivery_system.enums.DroneStatus;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface DroneRepository extends JpaRepository<Drone, Long>, JpaSpecificationExecutor<Drone> {
    List<Drone> findByStatus(DroneStatus status);

    Optional<Drone> findByCode(@NotBlank(message = "code is required") String code);
}
