package com.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.feign.RegisterFeignController;
import com.login.model.User;




@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private RegisterFeignController registerRepo;
	
	
	@Override
	public String validateUser(User uObj) {
		
		User userObj = this.registerRepo.getUserByName(uObj.getUserName());
		if(userObj != null) {
			if(userObj.getUserPassword().equals(uObj.getUserPassword())) {
				return "User Authenticated Successfully!!";
			}else {
				System.out.println("Wrong Password");
				return "Wrong Password";
			}
		}else {
			System.out.println("User Not Found!");
			return "User Not Found!";
		}
	}

}