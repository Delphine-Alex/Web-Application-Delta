package com.delta.blog.BlogClient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delta.blog.BlogClient.model.Article;
import com.delta.blog.BlogClient.repository.ArticleProxy;


@Service
public class ArticleService {
	@Autowired
	private ArticleProxy articleProxy;
	
	public Article getArticleById(Integer id) {
		return articleProxy.getArticleById(id);
	}

	public void addArticle(Article article) {
		articleProxy.addArticle(article);		
	}

	public void deleteById(Integer id) {
		articleProxy.deleteById(id);		
	}
	
}
