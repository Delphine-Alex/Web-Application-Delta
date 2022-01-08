package com.delta.blog.BlogClient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delta.blog.BlogClient.TokenContext;
import com.delta.blog.BlogClient.model.APIUser;
import com.delta.blog.BlogClient.repository.LoginProxy;

@Service
public class LoginService {

	@Autowired
	private TokenContext tokenContext;

	@Autowired
	private LoginProxy loginProxy;

	public void login(APIUser user) {
		String token = loginProxy.login(user);
		tokenContext.setToken(token);
	}
}
