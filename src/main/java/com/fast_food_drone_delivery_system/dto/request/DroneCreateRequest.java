package com.fast_food_drone_delivery_system.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DroneCreateRequest {
    @NotBlank(message = "code is required") String code;
    @NotNull(message = "status is required")
    String status;

    // optional coordinates
    @DecimalMin(value = "-90.0") @DecimalMax(value = "90.0") Double currentLat;
    @DecimalMin(value = "-180.0") @DecimalMax(value = "180.0") Double currentLng;

    @DecimalMin(value = "0.0") @DecimalMax(value = "100.0") Double batteryLevel;
}
