package com.userManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userManagement.entity.User;



public interface UserRepo extends JpaRepository<User, Integer> {

	public boolean existsByEmail(String emial);
	
	public User findByEmail(String email);
}
