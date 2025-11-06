package com.fast_food_drone_delivery_system.repository;

import com.fast_food_drone_delivery_system.entity.Drone;
import com.fast_food_drone_delivery_system.enums.DroneStatus;
import jakarta.persistence.LockModeType;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DroneRepository extends JpaRepository<Drone, Long>, JpaSpecificationExecutor<Drone> {
    List<Drone> findByStatus(DroneStatus status);

    Optional<Drone> findByCode(@NotBlank(message = "code is required") String code);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select d from Drone d where d.status = :status order by (d.currentLat - :lat)*(d.currentLat - :lat) + (d.currentLng - :lng)*(d.currentLng - :lng)")
    List<Drone> findAvailableDronesForUpdate(DroneStatus droneStatus, BigDecimal pickupLat, BigDecimal pickupLng);
}
