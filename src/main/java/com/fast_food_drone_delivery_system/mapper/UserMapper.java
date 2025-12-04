package com.fast_food_drone_delivery_system.mapper;

import com.fast_food_drone_delivery_system.dto.request.RestaurantRequest;
import com.fast_food_drone_delivery_system.dto.response.AddressResponse;
import com.fast_food_drone_delivery_system.dto.response.RestaurantResponse;
import com.fast_food_drone_delivery_system.dto.response.UserResponse;
import com.fast_food_drone_delivery_system.entity.Address;
import com.fast_food_drone_delivery_system.entity.Restaurant;
import com.fast_food_drone_delivery_system.entity.User;
import org.mapstruct.*;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "address", source = "address", qualifiedByName = "firstAddress")
    @Mapping(target = "fullName", source = "fullName")
    UserResponse toUserResponse(User user);

    @Named("firstAddress")
    default Address firstAddress(Set<Address> addresses) {
        return addresses.stream().findFirst().orElse(null);
    }

    // Add your Address to AddressResponse mapping here
    @Mapping(target = "street", source = "street")
    @Mapping(target = "city", source = "city")
    AddressResponse toAddressResponse(Address address);
}
