package com.fast_food_drone_delivery_system.service.impl;

import com.fast_food_drone_delivery_system.common.RestResponse;
import com.fast_food_drone_delivery_system.dto.request.RevenueRequest;
import com.fast_food_drone_delivery_system.dto.response.RevenueResponse;
import com.fast_food_drone_delivery_system.entity.Order;
import com.fast_food_drone_delivery_system.exception.AppException;
import com.fast_food_drone_delivery_system.exception.ErrorCode;
import com.fast_food_drone_delivery_system.repository.OrderRepository;
import com.fast_food_drone_delivery_system.repository.RestaurantRepository;
import com.fast_food_drone_delivery_system.service.IRevenueService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RevenueServiceImpl implements IRevenueService {
    OrderRepository orderRepository;
    RestaurantRepository restaurantRepository;

    @Override
    public RestResponse<RevenueResponse> getRevenue(RevenueRequest request) {
        if (request.getRestaurantId() == null) {
            throw new AppException(ErrorCode.DATASOURCE_NOT_FOUND);
        }

        LocalDate firstDayOfMonth = LocalDate.of(request.getYear(), request.getMonth(), 1);
        LocalDate lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth());

        Instant startDate = firstDayOfMonth.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Instant endDate = lastDayOfMonth.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant();

        log.info("start date is {}, {}", startDate, endDate);


        List<Order> orders = orderRepository.findByRestaurantIdAndCreatedAtBetween(
                request.getRestaurantId(), startDate, endDate
        );
        log.info("orders size is {}", orders.size());

        if (orders.isEmpty()) {
            return RestResponse.ok(new RevenueResponse(BigDecimal.ZERO, 0));
        }

        // Tính tổng doanh thu và tổng số đơn hàng
        BigDecimal totalRevenue = orders.stream()
                .map(Order::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        int totalOrders = orders.size();

        return RestResponse.ok(new RevenueResponse(totalRevenue, totalOrders));
    }

    @Override
    public RestResponse<Long> countRestaurants() {
        long count = restaurantRepository.count();

        log.info("Total restaurants in system: {}", count);

        return RestResponse.ok(count);
    }
}
