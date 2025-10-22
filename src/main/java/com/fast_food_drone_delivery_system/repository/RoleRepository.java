package com.fast_food_drone_delivery_system.repository;

import com.fast_food_drone_delivery_system.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
