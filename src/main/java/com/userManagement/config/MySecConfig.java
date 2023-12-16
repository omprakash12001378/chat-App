package com.userManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySecConfig {

	@Bean
	public UserDetailsService getUserDetailsService() {
		return new UserDtlsIMpl();
	}

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider getDaoAuthenticationProvider() {
		DaoAuthenticationProvider DaoAuthenticationProvider = new DaoAuthenticationProvider();
		DaoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
		DaoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
		return DaoAuthenticationProvider;
	}

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN").requestMatchers("/user/**").hasRole("USER")
				.requestMatchers("/**").permitAll().and().formLogin().loginPage("/login")
				.defaultSuccessUrl("/user/").and().csrf().disable();
		http.authenticationProvider(getDaoAuthenticationProvider());
		return http.build();
	}

	

//	@Bean
//	protected DaoAuthenticationProvider daoAuthenticationProvider( AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(getDaoAuthenticationProvider());
//		
//		return getDaoAuthenticationProvider();
//	}

}
