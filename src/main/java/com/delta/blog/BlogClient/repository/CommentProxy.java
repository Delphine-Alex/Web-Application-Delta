package com.delta.blog.BlogClient.repository;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.delta.blog.BlogClient.ApiProperties;
import com.delta.blog.BlogClient.TokenContext;
import com.delta.blog.BlogClient.model.Comment;

@Component
public class CommentProxy {
	@Autowired
	private ApiProperties props;

	@Autowired
	private TokenContext tokenContext;

	@SuppressWarnings("unused")
	private HttpHeaders createBasicAuthHeaders(String name, String password) {
		return new HttpHeaders() {
			private static final long serialVersionUID = 1L;
			{
				String auth = name + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}

	private HttpHeaders createTokenHeaders() {
		return new HttpHeaders() {
			private static final long serialVersionUID = 1L;
			{
				String authHeader = "Bearer " + tokenContext.getToken();
				set("Authorization", authHeader);
				System.out.println("Provided token is : " + authHeader);
			}
		};
	}

	public List<Comment> getComments() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Comment>> response = restTemplate.exchange(props.getPublicurl() + "/comments",
				HttpMethod.GET, new HttpEntity<>(createTokenHeaders()),
				new ParameterizedTypeReference<List<Comment>>() {
				});
		return response.getBody();
	}

	public Comment getCommentById(Integer id) {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Comment> response = restTemplate.exchange(props.getPublicurl() + "/comment/" + id,
				HttpMethod.GET, new HttpEntity<>(createTokenHeaders()), Comment.class);
		return response.getBody();
	}

	public void addComment(Comment comment) {
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<Comment> request = new HttpEntity<Comment>(comment, createTokenHeaders());

		restTemplate.exchange(props.getUrl() + "/comments", HttpMethod.POST, request, Comment.class);
	}

	public void deleteById(Integer id) {
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.exchange(props.getUrl() + "/comment/" + id,
				HttpMethod.DELETE, new HttpEntity<>(createTokenHeaders()), Comment.class);
	}

}
