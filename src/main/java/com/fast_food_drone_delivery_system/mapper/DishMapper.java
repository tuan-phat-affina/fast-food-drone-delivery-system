package com.fast_food_drone_delivery_system.mapper;

import com.fast_food_drone_delivery_system.dto.request.DishRequest;
import com.fast_food_drone_delivery_system.dto.request.RestaurantRequest;
import com.fast_food_drone_delivery_system.dto.response.DishResponse;
import com.fast_food_drone_delivery_system.dto.response.RestaurantResponse;
import com.fast_food_drone_delivery_system.entity.Dish;
import com.fast_food_drone_delivery_system.entity.Restaurant;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DishMapper {

    Dish toDish(DishRequest request);

    @Mapping(target = "restaurantId", source = "restaurant.id")
    @Mapping(target = "restaurantName", source = "restaurant.name")
    @Mapping(target = "discount", source = "discount")
    DishResponse toDishResponse(Dish request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDish(@MappingTarget Dish entity, DishRequest request);
}
