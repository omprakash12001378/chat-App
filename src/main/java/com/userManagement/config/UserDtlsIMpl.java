package com.userManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.userManagement.entity.User;
import com.userManagement.repo.UserRepo;
@Service
public class UserDtlsIMpl implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepo.findByEmail(email);

		if (user != null) {
			return new CustomUserDtls(user);
		}

		throw new UsernameNotFoundException("user not available");
	}

}
