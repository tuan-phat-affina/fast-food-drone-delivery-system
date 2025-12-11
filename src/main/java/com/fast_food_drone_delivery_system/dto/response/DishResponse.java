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
public class DishResponse {
    private String id;
    private String name;
    private String description;
    private Double price;
    private String status;
    private String restaurantId;
    private String restaurantName;
    private Double discount;
}
