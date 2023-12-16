package com.userManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.userManagement.entity.User;
import com.userManagement.repo.UserRepo;
import com.userManagement.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class homeController {
	@Autowired
	private UserService service;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@PostMapping("/do_register")
	public String createUser(@ModelAttribute User user, HttpSession session) {
//		System.out.println(user);
		boolean f = this.service.ExistsByEmail(user.getEmail());
		if (f) {    
			System.out.println("Email alredy exist");
			session.setAttribute("msg", "Email alredy exist");
		} else {
			
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User u = this.service.createUser(user);
			if (u != null) {
				System.out.println("registered sucessfully");
				session.setAttribute("msg", "Registered Sucessfully..");
			} else {
				session.setAttribute("msg", "Something went wrong");
			}
		}
		return "redirect:/register";
	}

}
