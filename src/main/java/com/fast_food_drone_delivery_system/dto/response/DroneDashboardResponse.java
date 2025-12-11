package com.fast_food_drone_delivery_system.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DroneDashboardResponse {
    private long totalDrones;
    private long available;
    private long delivering;
    private long needMaintenance;
}

