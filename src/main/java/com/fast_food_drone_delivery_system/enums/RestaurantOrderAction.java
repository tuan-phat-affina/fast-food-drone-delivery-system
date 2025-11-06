package com.fast_food_drone_delivery_system.enums;

public enum RestaurantOrderAction {
    ACCEPT,   // PENDING -> PREPARING
    REJECT,   // PENDING -> CANCELLED (with reason)
    CANCEL    // PENDING -> CANCELLED (restaurant initiated cancel)
}
