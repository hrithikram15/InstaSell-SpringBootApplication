package com.order.model;


public class OrderProductQuantity {
	
	private Integer productId;
	private Integer quantity;
	
	public OrderProductQuantity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderProductQuantity(Integer productId, Integer quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
