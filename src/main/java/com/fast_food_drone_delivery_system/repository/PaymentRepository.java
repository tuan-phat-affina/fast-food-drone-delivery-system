package com.fast_food_drone_delivery_system.repository;

import com.fast_food_drone_delivery_system.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository  extends JpaRepository<Payment, Long> {
}
