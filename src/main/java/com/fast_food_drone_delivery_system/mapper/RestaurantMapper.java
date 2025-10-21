package com.fast_food_drone_delivery_system.mapper;

import com.fast_food_drone_delivery_system.dto.request.DroneCreateRequest;
import com.fast_food_drone_delivery_system.dto.request.DroneUpdateRequest;
import com.fast_food_drone_delivery_system.dto.request.RestaurantRequest;
import com.fast_food_drone_delivery_system.dto.response.DroneResponse;
import com.fast_food_drone_delivery_system.dto.response.RestaurantResponse;
import com.fast_food_drone_delivery_system.entity.Drone;
import com.fast_food_drone_delivery_system.entity.Restaurant;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    Restaurant toRestaurant(RestaurantRequest request);

    RestaurantResponse toRestaurantResponse(Restaurant restaurant);

    @Mapping(target = "lastUpdated", ignore = true)
    @Mapping(target = "status", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRestaurant(@MappingTarget Restaurant entity, RestaurantRequest request);
}
