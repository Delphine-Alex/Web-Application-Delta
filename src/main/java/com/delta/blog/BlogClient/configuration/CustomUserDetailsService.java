package com.delta.blog.BlogClient.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.delta.blog.BlogClient.service.LoginService;


@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private LoginService loginService;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		com.delta.blog.BlogClient.model.User user = new com.delta.blog.BlogClient.model.User();
		user.setAuthor_name("Jinx");
		user.setPassword("password");
		loginService.login(user);
		
		User webUser = new User(
				"yaya", 
				new BCryptPasswordEncoder().encode("yaya"),
				getGrantedAuthorities());
		return webUser;
	}
	
	private List<GrantedAuthority> getGrantedAuthorities() {
		List<GrantedAuthority> authorities = 
				new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities;
	}
}
