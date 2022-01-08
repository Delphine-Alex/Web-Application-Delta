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
import com.delta.blog.BlogClient.model.Category;

@Component
public class CategoryProxy {
	@Autowired
	private ApiProperties props;
	
	@Autowired
	private TokenContext tokenContext;

	@SuppressWarnings("unused")
	private HttpHeaders createBasicAuthHeaders(String name, String password){
		return new HttpHeaders() {
			private static final long serialVersionUID = 1L;
			{
				String auth = name + ":" + password;
		        byte[] encodedAuth = Base64.encodeBase64( 
		            auth.getBytes(Charset.forName("US-ASCII")) );
		        String authHeader = "Basic " + new String( encodedAuth );
		        set( "Authorization", authHeader );
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
	
	public List<Category> getCategories() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<Category>> response =
				restTemplate.exchange(
						props.getPublicurl() + "/categories", 
						HttpMethod.GET, 
						new HttpEntity<>(createTokenHeaders()), 
						new ParameterizedTypeReference<List<Category>>() {}
					);
		return response.getBody();
	}

	public Category getCategoryById(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Category> response =
				restTemplate.exchange(
						props.getPublicurl() + "/category/" + id, 
						HttpMethod.GET, 
						new HttpEntity<>(createTokenHeaders()), 
						Category.class);
		return response.getBody();
	}
	public void addCategory(Category category) {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<Category> request = new HttpEntity<Category>(category, createTokenHeaders());
		
		restTemplate.exchange(
				props.getUrl() + "/categories",
				HttpMethod.POST,
				request,
				Category.class				
				);
	}	
	public void Category(Category category,Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<Category> request = new HttpEntity<Category>(category, createTokenHeaders());
		
		restTemplate.exchange(
				props.getUrl() + "/category/" + id,
				HttpMethod.PUT,
				request,
				Category.class				
				);
	}
	public void deleteCategoryById(Category category,Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<Category> request = new HttpEntity<Category>(category, createTokenHeaders());
		
		restTemplate.exchange(
				props.getUrl() + "/category/" + id,
				HttpMethod.DELETE,
				request,
				Category.class				
				);
	}
}
