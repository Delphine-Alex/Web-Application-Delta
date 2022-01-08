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
import com.delta.blog.BlogClient.model.Article;

@Component
public class ArticleProxy {
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

	public List<Article> getArticles() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Article>> response = restTemplate.exchange(props.getPublicurl() + "/articles",
				HttpMethod.GET, new HttpEntity<>(createTokenHeaders()),
				new ParameterizedTypeReference<List<Article>>() {
				});
		return response.getBody();
	}

	public Article getArticleById(Integer id) {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Article> response = restTemplate.exchange(props.getPublicurl() + "/article/" + id,
				HttpMethod.GET, new HttpEntity<>(createTokenHeaders()), Article.class);
		return response.getBody();
	}

	public void addArticle(Article article) {
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<Article> request = new HttpEntity<Article>(article, createTokenHeaders());

		restTemplate.exchange(props.getUrl() + "/articles", HttpMethod.POST, request, Article.class);
	}

	public void deleteById(Integer id) {
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.exchange(props.getUrl() + "/article/" + id,
				HttpMethod.DELETE, new HttpEntity<>(createTokenHeaders()), Article.class);
	}
}
