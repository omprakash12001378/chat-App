package com.userManagement.service;

import com.userManagement.entity.User;

public interface UserService {

	public User createUser(User user);
	
	public boolean ExistsByEmail(String email);
}
	
