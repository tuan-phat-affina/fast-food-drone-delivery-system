package com.fast_food_drone_delivery_system.dto.request;

import lombok.Data;

@Data
public class RevenueRequest {
    private Long restaurantId;  // ID nhà hàng
    private Integer year;           // Năm cần thống kê
    private Integer month;
}
