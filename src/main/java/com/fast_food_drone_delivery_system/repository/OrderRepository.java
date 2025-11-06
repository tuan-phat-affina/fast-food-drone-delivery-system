package com.fast_food_drone_delivery_system.repository;

import com.fast_food_drone_delivery_system.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Page<Order> findByCustomer_Id(Long customerId, Pageable p);
    Page<Order> findByRestaurant_Id(Long restaurantId, Pageable p);
}
