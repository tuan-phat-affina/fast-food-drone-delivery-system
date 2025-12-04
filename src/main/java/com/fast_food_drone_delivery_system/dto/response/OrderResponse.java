package com.fast_food_drone_delivery_system.dto.response;

import com.fast_food_drone_delivery_system.enums.DeliveryStatus;
import com.fast_food_drone_delivery_system.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderResponse (
        String id,
        Long customerId,
        String customerName,
        String customerPhone,
        Long restaurantId,
        String restaurantName,
        OrderStatus status,
        Double totalAmount,
        Double shippingFee,
        List<OrderItemResponse> items,
        DeliveryTaskSummary deliveryTask,
        Instant createdAt,
        Instant updatedAt
) {
    public record OrderItemResponse(
            String dishId,
            String dishName,
            Integer qty,
            Double unitPrice,
            Double subtotal
    ) {}

    public record DeliveryTaskSummary(
            String id,
            String droneId,
            DeliveryStatus status,
            BigDecimal pickupLat,
            BigDecimal pickupLng,
            BigDecimal dropoffLat,
            BigDecimal dropoffLng
    ) {}
}
