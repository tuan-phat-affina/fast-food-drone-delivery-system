package com.fast_food_drone_delivery_system.service;

import com.fast_food_drone_delivery_system.dto.model.OrderEvent;
import com.fast_food_drone_delivery_system.dto.model.OrderPendingCheckEvent;
import com.fast_food_drone_delivery_system.dto.model.PaymentEvent;

public interface EventPublisher {
    void publish(OrderEvent event);

    void publish(PaymentEvent event);

    void pulishEvent(OrderPendingCheckEvent event);
}
