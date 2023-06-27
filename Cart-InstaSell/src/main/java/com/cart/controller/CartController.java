package com.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cart.feignconfig.ProductFeignController;
import com.cart.feignconfig.UserFeignController;
import com.cart.model.Cart;
import com.cart.model.Product;
import com.cart.model.User;
import com.cart.service.CartServiceImpl;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartServiceImpl cartService;
	
	@Autowired
	private ProductFeignController productRepo;
	
	@Autowired
	private UserFeignController userRepo;
	
	@GetMapping({"/addToCart/{productId}/{userName}"})
	public Cart addTocart(@PathVariable(name="productId") Integer productId, @PathVariable(name="userName") String userName) {
		return cartService.addToCart(productId, userName);
		
	}
	
	@DeleteMapping({"/deleteCartItem/{cartId}"})
	public void deleteCartItem(@PathVariable(name= "cartId") Integer cartId) {
		cartService.deleteCartItem(cartId);		
	}
	
	
	
	@GetMapping({"/getCartDetails/{userName}"})
	public List<Cart> getCartDetails(@PathVariable String userName) {
		return cartService.getCartDetails(userName);
		
	}
	
	@GetMapping("/product/{productId}")
	public Product getProduct(@PathVariable Integer productId) {
		return productRepo.getProduct(productId);
	}
	
	@GetMapping("/{userName}")
	public User getUser(@PathVariable String userName) {
		return userRepo.getUserByName(userName);
	}
}
