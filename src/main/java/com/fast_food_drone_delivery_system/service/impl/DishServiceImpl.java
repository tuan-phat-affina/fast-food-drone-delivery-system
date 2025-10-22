package com.fast_food_drone_delivery_system.service.impl;

import com.fast_food_drone_delivery_system.common.IdGenerator;
import com.fast_food_drone_delivery_system.common.SearchHelper;
import com.fast_food_drone_delivery_system.dto.request.DishRequest;
import com.fast_food_drone_delivery_system.dto.response.DishResponse;
import com.fast_food_drone_delivery_system.dto.response.DroneResponse;
import com.fast_food_drone_delivery_system.dto.response.ListResponse;
import com.fast_food_drone_delivery_system.entity.Dish;
import com.fast_food_drone_delivery_system.entity.Drone;
import com.fast_food_drone_delivery_system.entity.Restaurant;
import com.fast_food_drone_delivery_system.enums.DishStatus;
import com.fast_food_drone_delivery_system.exception.AppException;
import com.fast_food_drone_delivery_system.exception.ErrorCode;
import com.fast_food_drone_delivery_system.mapper.DishMapper;
import com.fast_food_drone_delivery_system.repository.DishRepository;
import com.fast_food_drone_delivery_system.repository.RestaurantRepository;
import com.fast_food_drone_delivery_system.service.IDishService;
import io.github.perplexhub.rsql.RSQLJPASupport;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class DishServiceImpl implements IDishService {
    DishRepository dishRepository;
    RestaurantRepository restaurantRepository;
    DishMapper dishMapper;

    private static final List<String> SEARCH_FIELDS = List.of("name", "status", "price", "restaurant_id");

    @Override
    public DishResponse createDish(Long restaurantId, DishRequest req, Long ownerId) {
        Restaurant restaurant = restaurantRepository.findById(ownerId)
                .orElseThrow(() -> new AppException(ErrorCode.DATASOURCE_NOT_FOUND));

        if (!restaurant.getOwner().getId().equals(ownerId)) {
            throw new RuntimeException("Unauthorized to add dish to this restaurant");
        }

        Dish dish = dishMapper.toDish(req);
        dish.setId(IdGenerator.generateRandomId());
        dish.setRestaurant(restaurant);
        dish.setStatus(DishStatus.AVAILABLE.name());
        dish.setCreatedAt(Instant.now());
        return dishMapper.toDishResponse(dishRepository.save(dish));
    }

    @Override
    public ListResponse<DishResponse> getListDishes(int page, int size, String sort, String filter, String search, boolean all) {
        Specification<Dish> sortable = RSQLJPASupport.toSort(sort);
        Specification<Dish> filterable = RSQLJPASupport.toSpecification(filter);
        Specification<Dish> searchable = SearchHelper.parseSearchToken(search, SEARCH_FIELDS);
        Pageable pageable = all ? Pageable.unpaged() : PageRequest.of(page - 1, size);
        Page<DishResponse> responses = dishRepository
                .findAll(sortable.and(filterable).and(searchable), pageable)
                .map(dishMapper::toDishResponse);

        return ListResponse.of(responses);
    }

    @Override
    public DishResponse updateDish(Long dishId, DishRequest req, Long ownerId) {
        return null;
    }
}
