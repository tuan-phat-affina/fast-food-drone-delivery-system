package com.fast_food_drone_delivery_system.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestaurantRequest {
    private String name;
    private AddressRequest address;
    private RegisterRequest registerRequest;
    private String phone;
    private String email;
    private String description;
}
