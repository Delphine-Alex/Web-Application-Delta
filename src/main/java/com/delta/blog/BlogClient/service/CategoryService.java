package com.delta.blog.BlogClient.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delta.blog.BlogClient.model.Category;
import com.delta.blog.BlogClient.repository.CategoryProxy;

@Service
public class CategoryService {
	@Autowired
	private CategoryProxy categoryProxy;
	
	public List<Category> getCategories() {
		return categoryProxy.getCategories();
	}

	public Category getCategoryById(Integer id) {
		return categoryProxy.getCategoryById(id);
	}

	public void addCategory(Category category) {
		categoryProxy.addCategory(category);		
	}
	public void Category(Category category, Integer id) {
		categoryProxy.Category(category, id);		
	}
	public void deleteCategoryById(Category category,Integer id) {
		categoryProxy.deleteCategoryById(category,id);		
	}
}
