package com.fast_food_drone_delivery_system.service.impl;

import com.fast_food_drone_delivery_system.dto.request.DroneCreateRequest;
import com.fast_food_drone_delivery_system.dto.request.DroneLocationRequest;
import com.fast_food_drone_delivery_system.dto.request.DroneUpdateRequest;
import com.fast_food_drone_delivery_system.dto.response.DroneResponse;
import com.fast_food_drone_delivery_system.dto.response.ListResponse;
import com.fast_food_drone_delivery_system.service.IDroneService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroneServiceImpl implements IDroneService {

    @Override
    public ListResponse<DroneResponse> getListDronesResponseByStatus(int page, int size, String sort, String filter, String search, boolean all) {
        return null;
    }

    @Override
    public DroneResponse getDroneDetail(Long id) {
        return null;
    }

    @Override
    public DroneResponse createDrone(DroneCreateRequest req) {
        return null;
    }

    @Override
    public DroneResponse updateDrone(Long id, DroneUpdateRequest req) {
        return null;
    }

    @Override
    public DroneResponse updateDroneLocation(Long id, DroneLocationRequest req) {
        return null;
    }
}
