package com.fast_food_drone_delivery_system.dto.response;

import com.fast_food_drone_delivery_system.enums.DroneStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DroneResponse {
    private Long id;
    private String code;
    private DroneStatus status;
    private Double currentLat;
    private Double currentLng;
    private Double batteryLevel;
    private Instant lastUpdated;
}
