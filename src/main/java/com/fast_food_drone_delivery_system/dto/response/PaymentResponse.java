package com.fast_food_drone_delivery_system.dto.response;

import com.fast_food_drone_delivery_system.enums.PaymentMethod;
import com.fast_food_drone_delivery_system.enums.PaymentStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
public record PaymentResponse(
        Long id,
        Long orderId,
        PaymentMethod method,
        PaymentStatus status,
        BigDecimal amount,
        String transactionId,
        Instant transactionTime,
        String paymentUrl
) {}