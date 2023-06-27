package com.cart.model;

import com.cart.model.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {
	
	@Id
	@GeneratedValue
	private Integer cartId;
//	@OneToOne
//	private Product product;
//	@OneToOne
//	private User user;
	
	private Integer productId;
	private String userName;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cart(Integer productId, String userName) {
		super();
		this.productId = productId;
		this.userName = userName;
	}
	
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	
	
	
	
}
