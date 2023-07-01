package com.login.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.login.model.User;


@FeignClient(name="user-service", url="http://localhost:9090/user")
public interface RegisterFeignController {
	
	@GetMapping("/getUser/{userName}")
	public User getUserByName(@PathVariable String userName);
	
}
