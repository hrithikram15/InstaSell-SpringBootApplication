package com.order.feignconfig;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.order.model.Cart;


@FeignClient(name="cart-service", url="http://localhost:9092/cart")
public interface CartFeignController {
	
	@GetMapping("/getCartDetails/{userName}")
	public List<Cart> findByUserName(@PathVariable String userName);
	

	@DeleteMapping({"/deleteCartItem/{cartId}"})
	public void deleteCartItem(@PathVariable(name= "cartId") Integer cartId);



}
