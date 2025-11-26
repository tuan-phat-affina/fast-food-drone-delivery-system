package com.fast_food_drone_delivery_system.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestaurantResponse {
    private String id;
    private String name;
    private AddressResponse address;
    private String phone;
    private String description;
    private Long ownerId;
}
