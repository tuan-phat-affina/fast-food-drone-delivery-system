package com.fast_food_drone_delivery_system.service;

import com.fast_food_drone_delivery_system.dto.request.RestaurantRequest;
import com.fast_food_drone_delivery_system.dto.response.ListResponse;
import com.fast_food_drone_delivery_system.dto.response.RestaurantResponse;

public interface IRestaurantService {
    RestaurantResponse createRestaurant(Long ownerId, RestaurantRequest request);

    RestaurantResponse updateRestaurant(Long id, Long ownerId, RestaurantRequest req);

    ListResponse<RestaurantResponse> getListRestaurants(int page, int size, String sort, String filter, String search, boolean all);
}
