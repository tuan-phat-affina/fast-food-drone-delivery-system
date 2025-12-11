package com.fast_food_drone_delivery_system.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DishRequest {
    private String name;
    private String description;
    private Double price;
    private BigDecimal discount;
}
