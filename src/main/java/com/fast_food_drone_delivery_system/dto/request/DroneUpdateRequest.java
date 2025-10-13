package com.fast_food_drone_delivery_system.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DroneUpdateRequest {
    private String status; // should be one of DroneStatus values
    private Double currentLat;
    private Double currentLng;
    private Double batteryLevel;
}
