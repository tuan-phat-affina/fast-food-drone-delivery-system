package com.fast_food_drone_delivery_system.controller;

import com.fast_food_drone_delivery_system.common.RestResponse;
import com.fast_food_drone_delivery_system.dto.request.RestaurantRequest;
import com.fast_food_drone_delivery_system.dto.response.ListResponse;
import com.fast_food_drone_delivery_system.dto.response.RestaurantResponse;
import com.fast_food_drone_delivery_system.service.IRestaurantService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController extends BaseController {
    private final IRestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestResponse<RestaurantResponse>> createRestaurant(
            @RequestBody RestaurantRequest request,
            HttpServletRequest httpReq) {
        Long ownerId = extractUserIdFromRequest(httpReq);
        RestResponse<RestaurantResponse> response = restaurantService.createRestaurant(ownerId, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ListResponse<RestaurantResponse>> getListRestaurants(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "rating,desc") String sort,
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) boolean all) {
        return ResponseEntity.ok(restaurantService.getListRestaurants(page, size, sort, filter, search, all));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<RestaurantResponse>> updateRestaurant(
            @PathVariable Long id,
            HttpServletRequest httpReq,
            @RequestBody RestaurantRequest request) {
        Long ownerId = extractUserIdFromRequest(httpReq);
        RestResponse<RestaurantResponse> response = restaurantService.updateRestaurant(id, ownerId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<RestaurantResponse>> deactivateRestaurant(
            @PathVariable Long id) {
        RestResponse<RestaurantResponse> response = restaurantService.deactivateRestaurant(id);
        return ResponseEntity.ok(response);
    }
}
