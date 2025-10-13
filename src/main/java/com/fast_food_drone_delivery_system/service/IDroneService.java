package com.fast_food_drone_delivery_system.service;

import com.fast_food_drone_delivery_system.dto.request.DroneCreateRequest;
import com.fast_food_drone_delivery_system.dto.request.DroneLocationRequest;
import com.fast_food_drone_delivery_system.dto.request.DroneUpdateRequest;
import com.fast_food_drone_delivery_system.dto.response.DroneResponse;
import com.fast_food_drone_delivery_system.dto.response.ListResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface IDroneService {
    ListResponse<DroneResponse> getListDronesResponseByStatus(int page, int size, String sort, String filter, String search, boolean all);

    DroneResponse getDroneDetail(Long id);

    DroneResponse createDrone(@Valid DroneCreateRequest req);

    DroneResponse updateDrone(Long id, DroneUpdateRequest req);

    DroneResponse updateDroneLocation(Long id, @Valid DroneLocationRequest req);
//    List<DroneResponse> getListDronesResponseByStatus(String status);
}
