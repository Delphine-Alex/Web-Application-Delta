package com.delta.blog.BlogClient.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delta.blog.BlogClient.model.Article;
import com.delta.blog.BlogClient.repository.ArticleProxy;


@Service
public class ArticleService {
	@Autowired
	private ArticleProxy articleProxy;
	
	public List<Article> getArticles() {
		return articleProxy.getArticles();
	}

	public Article getArticleById(Integer id) {
		return articleProxy.getArticleById(id);
	}

	public void addArticle(Article article) {
		articleProxy.addArticle(article);		
	}
	public void Article(Article article, Integer id) {
		articleProxy.Article(article, id);		
	}
	public void deleteArticleById(Article article,Integer id) {
		articleProxy.deleteArticleById(article,id);		
	}
	
}
