package com.fast_food_drone_delivery_system.service.impl;

import com.fast_food_drone_delivery_system.common.RestResponse;
import com.fast_food_drone_delivery_system.dto.request.RevenueRequest;
import com.fast_food_drone_delivery_system.dto.response.DroneDashboardResponse;
import com.fast_food_drone_delivery_system.dto.response.RevenueResponse;
import com.fast_food_drone_delivery_system.entity.Order;
import com.fast_food_drone_delivery_system.enums.DroneStatus;
import com.fast_food_drone_delivery_system.exception.AppException;
import com.fast_food_drone_delivery_system.exception.ErrorCode;
import com.fast_food_drone_delivery_system.repository.DroneRepository;
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
    DroneRepository droneRepository;

    @Override
    public RestResponse<RevenueResponse> getRevenue(RevenueRequest request) {
        if (request.getRestaurantId() == null) {
            throw new AppException(ErrorCode.DATASOURCE_NOT_FOUND);
        }

        // Nếu không truyền năm → lấy năm hiện tại
        int year = (request.getYear() == null)
                ? LocalDate.now().getYear()
                : request.getYear();

        Instant startDate;
        Instant endDate;

        if (request.getMonth() == null) {
            // ❗ Không truyền tháng → lấy toàn bộ năm
            LocalDate startOfYear = LocalDate.of(year, 1, 1);
            LocalDate endOfYear = LocalDate.of(year, 12, 31);

            startDate = startOfYear.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
            endDate = endOfYear.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant();
        } else {
            // Có truyền month → lấy doanh thu theo tháng trong năm
            LocalDate firstDayOfMonth = LocalDate.of(year, request.getMonth(), 1);
            LocalDate lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth());

            startDate = firstDayOfMonth.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
            endDate = lastDayOfMonth.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant();
        }

        log.info("Revenue query range: {} - {}", startDate, endDate);

        List<Order> orders = orderRepository.findByRestaurantIdAndCreatedAtBetween(
                request.getRestaurantId(), startDate, endDate
        );

        log.info("Orders found: {}", orders.size());

        if (orders.isEmpty()) {
            return RestResponse.ok(new RevenueResponse(BigDecimal.ZERO, 0));
        }

        // Tính tổng doanh thu
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

    @Override
    public RestResponse<DroneDashboardResponse> getDroneStatusSnapshot() {
        long total = droneRepository.count();
        long available = droneRepository.countByStatus(DroneStatus.AVAILABLE);
        long delivering = droneRepository.countByStatus(DroneStatus.DELIVERING);

        // MAINTENANCE OR battery_level < 20%
        long needMaintenance = droneRepository
                .countByStatusOrBatteryLevelLessThan(
                        DroneStatus.MAINTENANCE,
                        new BigDecimal("20")
                );

        DroneDashboardResponse response = new DroneDashboardResponse(
                total,
                available,
                delivering,
                needMaintenance
        );

        return RestResponse.ok(response);
    }
}
