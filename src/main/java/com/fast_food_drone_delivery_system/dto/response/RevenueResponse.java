package com.fast_food_drone_delivery_system.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RevenueResponse {
    private BigDecimal totalRevenue;  // Tổng doanh thu trong tháng
    private int totalOrders;
}
