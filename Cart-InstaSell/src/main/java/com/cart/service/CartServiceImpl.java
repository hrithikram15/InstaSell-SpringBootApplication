package com.cart.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cart.feignconfig.ProductFeignController;
import com.cart.feignconfig.UserFeignController;
import com.cart.model.Cart;
import com.cart.model.Product;
import com.cart.model.User;
import com.cart.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private UserFeignController userRepo;
	
	@Autowired
	private ProductFeignController productRepo;
	

	@Override
	public List<Cart> getCartDetails(String userName) {
		User user = userRepo.getUserByName(userName);
		return cartRepo.findByUserName(userName);
	}

	@Override
	public Cart addToCart(Integer productId, String userName) {
		Product product = productRepo.getProduct(productId);
		
		User user = userRepo.getUserByName(userName);
		
		List<Cart> cartList = cartRepo.findByUserName(userName);
		List<Cart> filteredList = cartList.stream().filter(x -> x.getProductId() == productId).collect(Collectors.toList());
		
		if(filteredList.size() > 0) {
			return null;
		}
		
		
		if(product != null && user != null) {
			Cart cart = new Cart(productId, userName);
			return cartRepo.save(cart);
		}
		return null;
		
	}

	@Override
	public void deleteCartItem(Integer cartId) {
		 cartRepo.deleteById(cartId);

		
	}
	
	@Override
	public void deleteProduct(String userName, Integer productId){
		
		Cart userCart = cartRepo.findByUserNameAndProductId(userName, productId);
		cartRepo.deleteById(userCart.getCartId());
		
	}

}
