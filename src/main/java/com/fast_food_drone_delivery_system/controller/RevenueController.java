package com.fast_food_drone_delivery_system.controller;

import com.fast_food_drone_delivery_system.common.RestResponse;
import com.fast_food_drone_delivery_system.dto.request.RevenueRequest;
import com.fast_food_drone_delivery_system.dto.response.RevenueResponse;
import com.fast_food_drone_delivery_system.service.IRevenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/revenue")
@RequiredArgsConstructor
public class RevenueController {
    private final IRevenueService revenueService;

    @PostMapping("/monthly")
    public ResponseEntity<RestResponse<RevenueResponse>> getMonthlyRevenue(@RequestBody RevenueRequest request) {
        RestResponse<RevenueResponse> response = revenueService.getRevenue(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/restaurant")
    public ResponseEntity<RestResponse<Long>> getRestaurantCount() {
        RestResponse<Long> response = revenueService.countRestaurants();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
