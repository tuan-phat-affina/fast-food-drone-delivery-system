package com.fast_food_drone_delivery_system.service;

import com.fast_food_drone_delivery_system.common.RestResponse;
import com.fast_food_drone_delivery_system.dto.request.RevenueRequest;
import com.fast_food_drone_delivery_system.dto.response.RevenueResponse;

public interface IRevenueService {
    RestResponse<RevenueResponse> getRevenue(RevenueRequest request);

    RestResponse<Long> countRestaurants();
}
