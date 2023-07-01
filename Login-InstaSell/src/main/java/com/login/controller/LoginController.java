package com.login.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.model.User;
import com.login.service.LoginServiceImpl;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginServiceImpl loginService;
	

	private Map<String, String> map = new HashMap<>();
	
	@GetMapping("/home")
	public String homeHandler() {
		return "Directed to home page";
	}
	
	@GetMapping("/validate")
	public ResponseEntity<?> validateUserHandler(@RequestBody User uObj){
		System.out.println(uObj);
		try {
			String jwtToken=generateToken(uObj);
			map.put("message", "User Successfully LoggedIn");
			map.put("token", jwtToken);
		}catch(Exception e) {
			map.put("message", e.getMessage());

            map.put("token", null);

            return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
		}
		
		 return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	private String generateToken(User UObj) throws ServletException{
		String jwtToken = "";
		String flag = this.loginService.validateUser(UObj);
		if(flag == "User Not Found!") {
			throw new ServletException("Invalid Credentials");
		}else {
			jwtToken=Jwts.builder().setSubject(UObj.getUserName()).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 3000000)).signWith(SignatureAlgorithm.HS256, "secret key").compact();
		}
		return jwtToken;
	}

}
