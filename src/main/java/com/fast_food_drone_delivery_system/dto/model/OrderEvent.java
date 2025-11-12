package com.fast_food_drone_delivery_system.dto.model;

import com.fast_food_drone_delivery_system.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class OrderEvent {
    private Long orderId;
    private List<String> emailTo;
    private OrderStatus status;
    private Instant occurredAt;
    private Map<String, Object> metadata;
}