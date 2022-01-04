package com.delta.blog.BlogClient;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.delta.blog.blog")
public class ApiProperties {
	private String url; 
	private String publicurl;
	private String token;
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
