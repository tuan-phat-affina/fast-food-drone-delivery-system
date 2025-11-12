package com.fast_food_drone_delivery_system.service;

import com.fast_food_drone_delivery_system.common.RestResponse;
import com.fast_food_drone_delivery_system.dto.request.PaymentCreateRequest;
import com.fast_food_drone_delivery_system.dto.response.OrderResponse;
import com.fast_food_drone_delivery_system.dto.response.PaymentResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface IPaymentService {
    RestResponse<PaymentResponse> createPayment(String ipAddress, Long customerId, PaymentCreateRequest request);

    RestResponse<String> handleVnPayIpn(HttpServletRequest request);
}
