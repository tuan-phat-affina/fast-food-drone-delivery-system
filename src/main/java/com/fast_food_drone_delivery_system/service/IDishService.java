package com.fast_food_drone_delivery_system.service;

import com.fast_food_drone_delivery_system.common.RestResponse;
import com.fast_food_drone_delivery_system.dto.request.DishRequest;
import com.fast_food_drone_delivery_system.dto.response.DishResponse;
import com.fast_food_drone_delivery_system.dto.response.ListResponse;

public interface IDishService {
    RestResponse<DishResponse> createDish(Long restaurantId, DishRequest req, Long ownerId);

    RestResponse<ListResponse<DishResponse>> getListDishes(int page, int size, String sort, String filter, String search, boolean all);

    RestResponse<DishResponse> updateDish(Long dishId, DishRequest req, Long ownerId);
}
