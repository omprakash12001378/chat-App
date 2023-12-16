package com.userManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.userManagement.entity.User;
import com.userManagement.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	@Override
	public User createUser(User user) {

		user.setPassword(passwordEncode.encode(user.getPassword()));
		user.setRole("ROLE_USER");

		return userRepo.save(user);
	}

	public boolean checkEmail(String email) {

		return userRepo.existsByEmail(email);
	}

	@Override
	public boolean ExistsByEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
