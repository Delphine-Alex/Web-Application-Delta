package com.delta.blog.BlogClient.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.delta.blog.BlogClient.ApiProperties;
import com.delta.blog.BlogClient.model.APIUser;

@Repository
public class LoginProxy {

	@Autowired
	private ApiProperties props;

	public String login(APIUser user) {
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<APIUser> request = new HttpEntity<APIUser>(user);

		ResponseEntity<String> response = restTemplate.exchange(props.getPublicurl() + "/login", HttpMethod.POST,
				request, String.class);

		System.out.println("Body response : " + response.getBody());

		String token = response.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
		System.out.println("Received token is " + token);
		
		props.setUser_id(user.getId());
		props.setUsername(user.getName());

		return token;
	}

	public String getCurrentUser() {
		return props.getUsername();
	}

	public Integer getCurrentUserId() {
		return props.getUser_id();
	}

}
