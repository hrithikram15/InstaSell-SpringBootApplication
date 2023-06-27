package com.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class OrderInstaSellApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderInstaSellApplication.class, args);
	}

}
