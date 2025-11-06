package com.fast_food_drone_delivery_system.dto.request;

import com.fast_food_drone_delivery_system.enums.RestaurantOrderAction;
import jakarta.validation.constraints.NotNull;

public record UpdateOrderStatusRequest(
        @NotNull RestaurantOrderAction action,
        String reason // optional, required for REJECT maybe
) {}
