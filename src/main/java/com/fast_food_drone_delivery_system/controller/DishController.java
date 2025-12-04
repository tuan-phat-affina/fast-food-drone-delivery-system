package com.fast_food_drone_delivery_system.controller;

import com.fast_food_drone_delivery_system.common.RestResponse;
import com.fast_food_drone_delivery_system.dto.request.DishRequest;
import com.fast_food_drone_delivery_system.dto.response.DishResponse;
import com.fast_food_drone_delivery_system.dto.response.ListResponse;
import com.fast_food_drone_delivery_system.service.IDishService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dishes")
@RequiredArgsConstructor
public class DishController extends BaseController {
    private final IDishService dishService;

    @PostMapping("/{restaurantId}")
    public ResponseEntity<RestResponse<DishResponse>> createDish(
            @PathVariable Long restaurantId,
            @RequestBody DishRequest request,
            HttpServletRequest httpReq) {
        Long ownerId = extractUserIdFromRequest(httpReq);
        return ResponseEntity.ok(dishService.createDish(restaurantId, request, ownerId));
    }

    @GetMapping
    public ResponseEntity<RestResponse<ListResponse<DishResponse>>> getListDishes(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id,desc") String sort,
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) boolean all) {
        return ResponseEntity.ok(dishService.getListDishes(page, size, sort, filter, search, all));
    }
    @PutMapping("/{dishId}/restaurant/{restaurantId}")
    public ResponseEntity<RestResponse<DishResponse>> updateDish(
            @PathVariable Long dishId,
            @PathVariable Long restaurantId,
            HttpServletRequest httpReq,
            @RequestBody DishRequest request) {
        Long ownerId = extractUserIdFromRequest(httpReq);
        return ResponseEntity.ok(dishService.updateDish(dishId, restaurantId, request, ownerId));
    }
}
