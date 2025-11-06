package com.fast_food_drone_delivery_system.controller;

import com.fast_food_drone_delivery_system.common.RestResponse;
import com.fast_food_drone_delivery_system.dto.request.OrderCreateRequest;
import com.fast_food_drone_delivery_system.dto.response.OrderResponse;
import com.fast_food_drone_delivery_system.service.IOrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController extends BaseController {

    private final IOrderService orderService;
    @PostMapping
    public ResponseEntity<RestResponse<OrderResponse>> createOrder(
            @RequestBody @Valid OrderCreateRequest req,
            HttpServletRequest httpReq) {
        Long customerId = extractUserIdFromRequest(httpReq);
        RestResponse<OrderResponse> created = orderService.createOrder(customerId, req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

}
