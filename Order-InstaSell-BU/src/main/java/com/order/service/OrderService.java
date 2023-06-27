package com.order.service;

import java.util.List;

import com.order.model.OrderDetail;
import com.order.model.OrderInput;

public interface OrderService {
	public List<OrderDetail> getAllOrderDetails();
	
	public List<OrderDetail> getOrderDetails(String userName);
	
	public void placeOrder(OrderInput orderInput, boolean isSingleProductCheckout, String userName);
	
	
	

}
