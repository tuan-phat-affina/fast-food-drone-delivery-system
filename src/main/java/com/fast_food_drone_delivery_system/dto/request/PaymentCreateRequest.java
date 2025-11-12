package com.fast_food_drone_delivery_system.dto.request;

import com.fast_food_drone_delivery_system.enums.PaymentMethod;

public record PaymentCreateRequest(
        Long orderId,
        PaymentMethod method
) {}
