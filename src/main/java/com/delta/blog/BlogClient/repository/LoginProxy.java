package com.delta.blog.BlogClient.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.delta.blog.BlogClient.ApiProperties;
import com.delta.blog.BlogClient.model.User;

@Repository
public class LoginProxy {
	@Autowired
	private ApiProperties props;
	
	public String login(User user) {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<User> request = 
				new HttpEntity<User>(user);
		
		ResponseEntity<String> response = restTemplate.exchange(
				props.getPublicurl() + "/login",
				HttpMethod.POST,
				request,
				String.class				
				);
		System.out.println("Body response : " + response.getBody());
		
		String token = response.getHeaders()
				.get(HttpHeaders.AUTHORIZATION).get(0);
		System.out.println("Received token is " + token);
		
		props.setToken(token);
		
		return token;
	}
}
