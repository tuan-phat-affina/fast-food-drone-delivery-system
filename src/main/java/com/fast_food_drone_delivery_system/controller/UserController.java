package com.fast_food_drone_delivery_system.controller;

import com.fast_food_drone_delivery_system.dto.response.ListResponse;
import com.fast_food_drone_delivery_system.dto.response.RestaurantResponse;
import com.fast_food_drone_delivery_system.dto.response.UserResponse;
import com.fast_food_drone_delivery_system.repository.UserRepository;
import com.fast_food_drone_delivery_system.service.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final IAuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<ListResponse<UserResponse>> getListUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "fullName,desc") String sort,
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) boolean all) {
        return ResponseEntity.ok(authenticationService.getListUsers(page, size, sort, filter, search, all));
    }
}
