package com.fast_food_drone_delivery_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@SpringBootApplication
@EnableScheduling
public class FastFoodDroneDeliverySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastFoodDroneDeliverySystemApplication.class, args);
	}

}
