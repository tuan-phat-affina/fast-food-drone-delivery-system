package com.fast_food_drone_delivery_system.service;

import com.fast_food_drone_delivery_system.common.RestResponse;
import com.fast_food_drone_delivery_system.dto.request.OrderCreateRequest;
import com.fast_food_drone_delivery_system.dto.request.UpdateOrderStatusRequest;
import com.fast_food_drone_delivery_system.dto.response.ListResponse;
import com.fast_food_drone_delivery_system.dto.response.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService {
    RestResponse<OrderResponse> createOrder(Long customerId, OrderCreateRequest req);
    RestResponse<OrderResponse> getOrder(Long userId, Long orderId); // authorize inside
    RestResponse<ListResponse<OrderResponse>>getListOrdersByFilter(int page, int size, String sort, String filter, String search, boolean all);

    RestResponse<OrderResponse> restaurantHandleOrderStatus(String ipAddress, Long ownerId, Long orderId, UpdateOrderStatusRequest request);
    RestResponse<OrderResponse> restaurantReadyForPickup(Long ownerId, Long orderId); // assign drone -> SHIPPING

    RestResponse<OrderResponse> confirmDelivered(Long userIdOrSystemId, Long orderId); // allow DRONE_SYSTEM role to call
}
