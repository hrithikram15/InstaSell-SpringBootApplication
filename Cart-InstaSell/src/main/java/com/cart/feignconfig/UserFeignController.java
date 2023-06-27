package com.cart.feignconfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cart.model.User;


@FeignClient(name="user-service", url="http://localhost:9090/user")
public interface UserFeignController {
	
	@GetMapping("/getUser/{userName}")
	public User getUserByName(@PathVariable String userName);
}

