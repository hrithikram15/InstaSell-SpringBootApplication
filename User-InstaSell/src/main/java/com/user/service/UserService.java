package com.user.service;

import java.util.List;
import java.util.Optional;

import com.user.exception.UserAlreadyExistsException;
import com.user.exception.UserNotFoundException;
import com.user.model.User;

public interface UserService {
	public User saveUser(User user) throws UserAlreadyExistsException;
	
	public List<User> getAllUsers();
	
	public Optional<User> getUser(String userName) throws UserNotFoundException;

}
