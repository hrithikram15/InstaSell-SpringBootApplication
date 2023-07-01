package com.cart.service;

import java.util.List;

import com.cart.model.Cart;

public interface CartService {
	public List<Cart> getCartDetails(String userName);
	
	public Cart addToCart(Integer productId, String userName);
	
	public void deleteCartItem(Integer cartId);
	
	public void deleteProduct(String userName, Integer productId);
}
