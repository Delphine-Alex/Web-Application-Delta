package com.delta.blog.BlogClient;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.delta.blog.blog")
public class ApiProperties {
	private String url;
	private String publicurl;

	private String username;
	private Integer user_id;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPublicurl() {
		return publicurl;
	}

	public void setPublicurl(String publicurl) {
		this.publicurl = publicurl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

}
