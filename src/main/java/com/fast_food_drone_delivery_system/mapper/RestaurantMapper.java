package com.fast_food_drone_delivery_system.mapper;

import com.fast_food_drone_delivery_system.dto.request.DroneCreateRequest;
import com.fast_food_drone_delivery_system.dto.request.DroneUpdateRequest;
import com.fast_food_drone_delivery_system.dto.request.RestaurantRequest;
import com.fast_food_drone_delivery_system.dto.response.AddressResponse;
import com.fast_food_drone_delivery_system.dto.response.DroneResponse;
import com.fast_food_drone_delivery_system.dto.response.RestaurantResponse;
import com.fast_food_drone_delivery_system.entity.Address;
import com.fast_food_drone_delivery_system.entity.Drone;
import com.fast_food_drone_delivery_system.entity.Restaurant;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    @Mapping(target = "address", ignore = true)
    Restaurant toRestaurant(RestaurantRequest request);

    @Mapping(target = "ownerId", source = "owner.id")
    @Mapping(target = "status", source = "status")
    RestaurantResponse toRestaurantResponse(Restaurant restaurant);

    @Mapping(target = "address", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRestaurant(@MappingTarget Restaurant entity, RestaurantRequest request);
}
