package com.fast_food_drone_delivery_system.controller;

import com.fast_food_drone_delivery_system.dto.request.DishRequest;
import com.fast_food_drone_delivery_system.dto.response.DishResponse;
import com.fast_food_drone_delivery_system.dto.response.ListResponse;
import com.fast_food_drone_delivery_system.service.IDishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dishes")
@RequiredArgsConstructor
public class DishController {
    private final IDishService dishService;

    @PostMapping
    public ResponseEntity<DishResponse> createDish(
            @RequestParam Long restaurantId,
            @RequestHeader("X-Owner-Id") Long ownerId,
            @RequestBody DishRequest request) {
        return ResponseEntity.ok(dishService.createDish(restaurantId, request, ownerId));
    }

    @GetMapping
    public ResponseEntity<ListResponse<DishResponse>> getListDishes(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "code,desc") String sort,
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) boolean all) {
        return ResponseEntity.ok(dishService.getListDishes(page, size, sort, filter, search, all));
    }

    @PutMapping("/{dishId}")
    public ResponseEntity<DishResponse> updateDish(
            @PathVariable Long dishId,
            @RequestHeader("X-Owner-Id") Long ownerId,
            @RequestBody DishRequest request) {
        return ResponseEntity.ok(dishService.updateDish(dishId, request, ownerId));
    }
}
