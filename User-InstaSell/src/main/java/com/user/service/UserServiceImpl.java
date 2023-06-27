package com.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.exception.UserAlreadyExistsException;
import com.user.exception.UserNotFoundException;
import com.user.model.User;
import com.user.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public User saveUser(User user) throws UserAlreadyExistsException {
		// TODO Auto-generated method stub
		Optional<User> optional = this.userRepo.findById(user.getUserName());
		
		if(optional.isPresent()) {
			throw new UserAlreadyExistsException();
		}else {
			User savedUser = this.userRepo.save(user);
			return savedUser;

		}
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		
		return this.userRepo.findAll();
	}

	@Override
	public Optional<User> getUser(String userName) throws UserNotFoundException {
		// TODO Auto-generated method stub
		
		return userRepo.findById(userName);
	}
}
