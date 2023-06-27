package com.order.model;

public class Cart {
	
	private Integer cartId;
	private Integer productId;
	private String userName;
	
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cart(Integer cartId, Integer productId, String userName) {
		super();
		this.cartId = cartId;
		this.productId = productId;
		this.userName = userName;
	}
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
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
	
}
