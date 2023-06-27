package com.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CartInstaSellApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartInstaSellApplication.class, args);
	}

}
