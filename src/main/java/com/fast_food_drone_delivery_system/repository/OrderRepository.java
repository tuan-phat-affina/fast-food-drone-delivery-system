package com.fast_food_drone_delivery_system.repository;

import com.fast_food_drone_delivery_system.entity.Order;
import com.fast_food_drone_delivery_system.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    Page<Order> findByCustomer_Id(Long customerId, Pageable p);
    Page<Order> findByRestaurant_Id(Long restaurantId, Pageable p);

    List<Order> findByStatusAndCreatedAtBefore(OrderStatus orderStatus, Instant fiveMinutesAgo);
}
