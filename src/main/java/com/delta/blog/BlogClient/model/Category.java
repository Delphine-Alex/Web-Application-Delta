package com.delta.blog.BlogClient.model;

import java.util.ArrayList;
import java.util.List;


public class Category {
	private Integer id;
	private String name;
	private List<Article> articles=new ArrayList<>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
}
