package com.fast_food_drone_delivery_system.common;

import com.fast_food_drone_delivery_system.dto.model.OrderEvent;
import com.fast_food_drone_delivery_system.dto.model.OrderPendingCheckEvent;
import com.fast_food_drone_delivery_system.dto.model.PaymentEvent;
import com.fast_food_drone_delivery_system.service.EventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AsyncEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher springPublisher;

    public AsyncEventPublisher(ApplicationEventPublisher springPublisher) {
        this.springPublisher = springPublisher;
    }

    @Override
    public void publish(OrderEvent event) {
        log.info("Publishing order event: {}", event);
        springPublisher.publishEvent(event);
    }

    @Override
    public void publish(PaymentEvent event) {
        log.info("Publishing payment event: {}", event);
        springPublisher.publishEvent(event);
    }

    @Override
    public void pulishEvent(OrderPendingCheckEvent event) {
        log.info("Publishing checking pending order event: {}", event);
        springPublisher.publishEvent(event);
    }
}
