package com.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.exception.UserAlreadyExistsException;
import com.user.model.User;
import com.user.service.UserServiceImpl;

@CrossOrigin(origins="http://localhost:4200")

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	private ResponseEntity<?> responseEntity;
	
	
	@PostMapping("/register")
	public ResponseEntity<?> saveUserHandler(@RequestBody User user) throws UserAlreadyExistsException{
		try {
			User createdUser = userService.saveUser(user);
			this.responseEntity = new ResponseEntity<>(createdUser,HttpStatus.CREATED);
		} catch (UserAlreadyExistsException exception) {
			throw exception;
		}
		catch(Exception e) {
			System.out.println(e);
			this.responseEntity = new ResponseEntity<>("Some internal error occured..", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return this.responseEntity;
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllUsers(){
		try {
			List<User> usersList = this.userService.getAllUsers();
			this.responseEntity = new ResponseEntity<>(usersList,HttpStatus.OK);
		} catch (Exception e) {
			this.responseEntity = new ResponseEntity<>("Some internal error occured..", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return this.responseEntity;
	}
	
	@GetMapping("/getUser/{userName}")
	public ResponseEntity<?> getUserByName(@PathVariable String userName){
		try {
			Optional<User> user = this.userService.getUser(userName);
			this.responseEntity = new ResponseEntity<>(user,HttpStatus.OK);


		}catch (Exception e) {
			this.responseEntity = new ResponseEntity<>("Some internal error occured..", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return this.responseEntity;

		
	}
	


}
