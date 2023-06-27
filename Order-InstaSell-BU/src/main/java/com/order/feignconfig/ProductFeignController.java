package com.order.feignconfig;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.order.model.Product;


@FeignClient(name="product-service", url="http://localhost:9091/product")
public interface ProductFeignController {
	
	@GetMapping("/getProduct/{productId}")
	public Product getProduct(@PathVariable Integer productId);

}