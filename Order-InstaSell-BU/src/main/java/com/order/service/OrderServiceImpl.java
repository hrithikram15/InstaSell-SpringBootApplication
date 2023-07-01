package com.order.service;

import java.util.*;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.feignconfig.CartFeignController;
import com.order.feignconfig.ProductFeignController;
import com.order.feignconfig.UserFeignController;
import com.order.model.Cart;
import com.order.model.OrderDetail;
import com.order.model.OrderInput;
import com.order.model.OrderProductQuantity;
import com.order.model.Product;
import com.order.model.User;
import com.order.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	private static final String ORDER_PLACED = "Placed";  
	private static final String ORDER_CANCELLED = "Cancelled";  

	
	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private UserFeignController userRepo;
	
	@Autowired
	private ProductFeignController productRepo;
	
	@Autowired
	private CartFeignController cartRepo;

	@Override
	public List<OrderDetail> getAllOrderDetails() {
		
		List<OrderDetail> orderDetails = new ArrayList<>();
		orderRepo.findAll().forEach(e -> orderDetails.add(e));
		
		return orderDetails;
	}

	@Override
	public List<OrderDetail> getOrderDetails(String userName) {
	User user = userRepo.getUserByName(userName);
					
		return orderRepo.findByUserName(userName);
	}

	@Override
	public void placeOrder(OrderInput orderInput, boolean isSingleProductCheckout, String userName) {
List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();
		
		for(OrderProductQuantity o: productQuantityList) {
			Product product = productRepo.getProduct(o.getProductId());
//			
//			User user= userRepo.getUserByName(userName);
//			
			Integer productId = o.getProductId();
	       

			
			OrderDetail orderDetail = new OrderDetail(
					orderInput.getFullName(),
					orderInput.getFullAddress(),
					orderInput.getContactNumber(),
					orderInput.getAlternateContactNumber(),
					ORDER_PLACED,
					product.getProductDiscountedPrice()*o.getQuantity(),
					productId,
					userName);
			
			if(!isSingleProductCheckout) {
				List<Cart> carts= cartRepo.findByUserName(userName);
				carts.stream().forEach(x -> cartRepo.deleteCartItem(x.getCartId()));			
				
			}
			
			
			
			orderRepo.save(orderDetail);
		}
		
	}

	@Override
	public Optional<OrderDetail> getOrderDetailsById(Integer orderId) {
		// TODO Auto-generated method stub
		Optional<OrderDetail> optional = this.orderRepo.findById(orderId);
		
		
		
		return optional;
	}

	@Override
	public OrderDetail cancelOrderDetailsById(OrderDetail order, Integer orderId) {
		// TODO Auto-generated method stub
		Optional<OrderDetail> orders = this.orderRepo.findById(orderId);
		
		OrderDetail obj = null;
		OrderDetail updatedOrder = null;
		
		obj = orders.get();
		
		obj.setOrderAlternateContactNumber(order.getOrderAlternateContactNumber());
		obj.setOrderContactNumber(order.getOrderContactNumber());
		obj.setOrderAmount(order.getOrderAmount());
		obj.setOrderFullName(order.getOrderFullName());
		obj.setOrderFullOrder(order.getOrderFullOrder());
		obj.setProductId(order.getProductId());
		obj.setUserName(order.getUserName());
		obj.setOrderStatus(ORDER_CANCELLED);
		
		updatedOrder = this.orderRepo.save(obj);
		
		
		return updatedOrder;
	}
}
