package com.fast_food_drone_delivery_system.service.impl;

import com.fast_food_drone_delivery_system.common.IdGenerator;
import com.fast_food_drone_delivery_system.common.RestResponse;
import com.fast_food_drone_delivery_system.dto.request.OrderCreateRequest;
import com.fast_food_drone_delivery_system.dto.request.UpdateOrderStatusRequest;
import com.fast_food_drone_delivery_system.dto.response.OrderResponse;
import com.fast_food_drone_delivery_system.entity.*;
import com.fast_food_drone_delivery_system.enums.*;
import com.fast_food_drone_delivery_system.exception.AppException;
import com.fast_food_drone_delivery_system.exception.ErrorCode;
import com.fast_food_drone_delivery_system.mapper.OrderMapper;
import com.fast_food_drone_delivery_system.repository.*;
import com.fast_food_drone_delivery_system.service.IOrderService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderServiceImpl implements IOrderService {
    OrderRepository orderRepository;
    DeliveryTaskRepository deliveryTaskRepository;
    DishRepository dishRepository;
    UserRepository userRepository;
    RestaurantRepository restaurantRepository;
    DroneRepository droneRepository;
    AddressRepository addressRepository;
    OrderMapper orderMapper;

    @Transactional
    @Override
    public RestResponse<OrderResponse> createOrder(Long customerId, OrderCreateRequest req) {
        try {
            log.info("Start to create order with request: {} and {}", req, customerId);
            if (req.deliveryAddress() != null && req.deliveryAddressId() != null) {
                throw new AppException(ErrorCode.BUSINESS_INVALID_SEQUENCE);
            }

            User customer = userRepository.findById(customerId)
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

            Order order = new Order();
            Address deliveryAddress = null;
            if (req.deliveryAddress() != null) {
                deliveryAddress = Address.builder()
                        .id(IdGenerator.generateRandomId())
                        .user(customer)
                        .street(req.deliveryAddress().getStreet())
                        .city(req.deliveryAddress().getCity())
                        .latitude(req.deliveryAddress().getLatitude())
                        .longitude(req.deliveryAddress().getLongitude())
                        .type(AddressTypeStatus.valueOf(req.deliveryAddress().getType()).name())
                        .build();
                addressRepository.save(deliveryAddress);
                order.setDeliveryAddress(deliveryAddress);
            } else if (req.deliveryAddressId() != null) {
                deliveryAddress = addressRepository.findById(req.deliveryAddressId())
                        .orElseThrow(() -> new AppException(ErrorCode.DATASOURCE_NOT_FOUND));
                order.setDeliveryAddress(deliveryAddress);
            }

            Restaurant restaurant = restaurantRepository.findById(req.restaurantId())
                    .orElseThrow(() -> new AppException(ErrorCode.RESTAURANT_NOT_FOUND));

            order.setId(IdGenerator.generateRandomId());
            order.setCustomer(customer);
            order.setRestaurant(restaurant);
            order.setStatus(OrderStatus.PENDING);
            order.setCreatedAt(Instant.now());
            order.setUpdatedAt(Instant.now());

            double total = 0.0;
            List<OrderItem> items = new ArrayList<>();
            for (OrderCreateRequest.OrderItemRequest item : req.items()) {
                Dish dish = dishRepository.findById(item.dishId())
                        .orElseThrow(() -> new AppException(ErrorCode.DISH_NOT_FOUND));

                if (!DishStatus.AVAILABLE.name().equals(dish.getStatus())) {
                    throw new AppException(ErrorCode.DISH_SERVING_STOPPED);
                }

                double unitPrice = dish.getPrice().doubleValue();
                double subtotal = unitPrice * item.quantity();
                OrderItem orderItem = OrderItem.builder()
                        .id(IdGenerator.generateRandomId())
                        .dish(dish)
                        .quantity(item.quantity())
                        .unitPrice(BigDecimal.valueOf(unitPrice))
                        .subtotal(BigDecimal.valueOf(subtotal))
                        .order(order)
                        .build();
                items.add(orderItem);
                total += subtotal;
            }

            // 4. Compute shipping fee (simple rule: base fee + distance -> here stub as constant)
            double shippingFee = computeShippingFee(restaurant, deliveryAddress.getId());
            order.setShippingFee(BigDecimal.valueOf(shippingFee));
            total += shippingFee;
            order.setTotalAmount(BigDecimal.valueOf(total));

            order.setItems(items);
            // cascade persist will save items
            Order saved = orderRepository.save(order);

            // TODO: if paymentMethod is ONLINE, create Payment entity + call payment gateway (out of scope here)
            return RestResponse.ok(orderMapper.toOrderResponse(saved));
        } catch (AppException e) {
            log.error(e.getMessage());
            throw e;
        }

    }

    @Override
    public RestResponse<OrderResponse> getOrder(Long userId, Long orderId) {
        return null;
    }

    @Override
    public RestResponse<Page<OrderResponse>> listCustomerOrders(Long customerId, Pageable p) {
        return null;
    }

    @Override
    public RestResponse<Page<OrderResponse>> listRestaurantOrders(Long ownerId, Long restaurantId, Pageable p) {
        return null;
    }

    @Override
    public RestResponse<OrderResponse> restaurantHandleOrderStatus(Long ownerId, Long orderId, UpdateOrderStatusRequest request) {
        try {
            log.info("Start to handle restaurant accept request: {}", orderId);
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));

            if (!order.getRestaurant().getOwner().getId().equals(ownerId)) {
                throw new AppException(ErrorCode.UNAUTHORIZED_TO_UPDATE_THIS_RESOURCE);
            }
            if (order.getStatus() != OrderStatus.PENDING) {
                throw new AppException(ErrorCode.BUSINESS_INVALID_SEQUENCE);
            }

            switch (request.action()) {
                case ACCEPT -> {
                    order.setStatus(OrderStatus.PREPARING);
                    order.setUpdatedAt(Instant.now());
                    orderRepository.save(order);
                }

                case REJECT, CANCEL -> {
                    order.setStatus(OrderStatus.CANCELLED);
                    order.setUpdatedAt(Instant.now());
                    orderRepository.save(order);
                }
            }
            return RestResponse.ok(orderMapper.toOrderResponse(orderRepository.save(order)));
        } catch (AppException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public RestResponse<OrderResponse> restaurantReadyForPickup(Long ownerId, Long orderId) {
        try {
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));

            if (!order.getRestaurant().getOwner().getId().equals(ownerId)) {
                throw new AppException(ErrorCode.UNAUTHORIZED_TO_UPDATE_THIS_RESOURCE);
            }
            if (order.getStatus() != OrderStatus.PREPARING) {
                throw new AppException(ErrorCode.BUSINESS_INVALID_SEQUENCE);
            }

            if (order.getDeliveryAddress().getLatitude() == null
                    || order.getDeliveryAddress().getLongitude() == null) {
                throw new AppException(ErrorCode.BUSINESS_INVALID_SEQUENCE);
            }

            if (order.getRestaurant().getAddress().getLatitude() == null
                    || order.getRestaurant().getAddress().getLongitude() == null) {
                throw new AppException(ErrorCode.BUSINESS_INVALID_SEQUENCE);
            }

            // Determine pickup coordinates (restaurant address) and dropoff (customer address)
            // For demo: we'll try to use restaurant owner address or stored address table. Assume restaurant has coordinates.
            BigDecimal pickupLat = order.getRestaurant().getAddress().getLatitude();
            BigDecimal pickupLng = order.getRestaurant().getAddress().getLongitude();

            BigDecimal dropOffLat = order.getDeliveryAddress().getLatitude();
            BigDecimal dropOffLng = order.getDeliveryAddress().getLongitude();

            // 1. Find nearest AVAILABLE drone with pessimistic lock
            List<Drone> candidates = droneRepository.findAvailableDronesForUpdate(DroneStatus.AVAILABLE, pickupLat, pickupLng);
            if (candidates == null || candidates.isEmpty()) {
                throw new AppException(ErrorCode.DRONE_UNAVAILABLE);
            }
            Drone chosen = candidates.get(0); // query ordered by distance

            // 2. Update drone status to DELIVERING (locked row so others cannot pick it)
            chosen.setStatus(DroneStatus.DELIVERING.name());
            droneRepository.save(chosen);

            // 3. Create DeliveryTask and link to order
            DeliveryTask task = DeliveryTask.builder()
                    .order(order)
                    .drone(chosen)
                    .pickupLat(pickupLat).pickupLng(pickupLng)
                    .dropoffLat(dropOffLat).dropoffLng(dropOffLng)
                    .status(DeliveryStatus.IN_PROGRESS)
                    .assignedAt(Instant.now())
                    .build();

            deliveryTaskRepository.save(task);

            // 4. update order status -> SHIPPING
            order.setStatus(OrderStatus.SHIPPING);
            order.setDeliveryTask(task);
            order.setUpdatedAt(Instant.now());
            Order saved = orderRepository.save(order);

            // OPTIONAL: publish event to Drone System (message queue) to instruct drone to pickup
            // publishDeliveryAssignedEvent(task);

            return RestResponse.ok(orderMapper.toOrderResponse(saved));
        } catch (AppException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public RestResponse<OrderResponse> confirmDelivered(Long userIdOrSystemId, Long orderId) {
        return null;
    }

    private double computeShippingFee(Restaurant restaurant, Long deliveryAddressId) {
        // Todo: For now fixed fee; in real app compute distance and formula.
        return 5.0;
    }

}
