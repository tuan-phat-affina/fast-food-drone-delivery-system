package com.fast_food_drone_delivery_system.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {
    private String id;
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private AddressResponse address;
}
