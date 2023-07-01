package com.order.order;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.model.OrderDetail;
import com.order.model.OrderInput;
import com.order.service.OrderServiceImpl;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderServiceImpl orderRepo;
	
	@PostMapping({"/placeOrder/{isSingleProductCheckout}/{userName}"})
	public void placeOrder(@PathVariable(name= "isSingleProductCheckout") boolean isSingleProductCheckout,  @PathVariable(name="userName") String userName, @RequestBody OrderInput orderInput) {
		orderRepo.placeOrder(orderInput, isSingleProductCheckout, userName);
		
	}
	
	@GetMapping({"/getOrderDetails/{userName}"})
	public List<OrderDetail> getOrderDetails(@PathVariable String userName) {
		return orderRepo.getOrderDetails(userName);
		
	}
	
	@GetMapping({"/getAllOrderDetails"})
	public List<OrderDetail> getAllOrderDetails() {
		return orderRepo.getAllOrderDetails();
	}
	
	@GetMapping({"/getOrderDetailsById/{orderId}"})
	public Optional<OrderDetail> getOrderDetails(@PathVariable Integer orderId) {
		return orderRepo.getOrderDetailsById(orderId);
		
	}
	
	@PutMapping({"/updateOrder/{orderId}"})
	public OrderDetail updateOrderDetails(@RequestBody OrderDetail order, @PathVariable Integer orderId) {
		return orderRepo.cancelOrderDetailsById(order, orderId);
		
	}

}
