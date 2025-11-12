package com.fast_food_drone_delivery_system.dto.model;

import com.fast_food_drone_delivery_system.enums.PaymentMethod;
import com.fast_food_drone_delivery_system.enums.PaymentStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentEvent {
    Long paymentId;
    Long orderId;
    BigDecimal amount;
    PaymentMethod method;
    PaymentStatus status;
    Instant occurredAt;
    Map<String, Object> metadata;
}
