package com.fast_food_drone_delivery_system.mapper;

import com.fast_food_drone_delivery_system.dto.request.DroneCreateRequest;
import com.fast_food_drone_delivery_system.dto.request.DroneUpdateRequest;
import com.fast_food_drone_delivery_system.dto.response.DroneResponse;
import com.fast_food_drone_delivery_system.entity.Drone;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DroneMapper {

    @Mapping(target = "lastUpdated", ignore = true)
    @Mapping(target = "status", ignore = true)
    Drone toDrone(DroneCreateRequest request);

    DroneResponse toDroneResponse(Drone drone);

    @Mapping(target = "lastUpdated", ignore = true)
    @Mapping(target = "status", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDrone(@MappingTarget Drone entity, DroneUpdateRequest request);
}
