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
		if (category.getId() == null) {
			categoryProxy.addCategory(category);
		} else {
			categoryProxy.updateCategory(category);			
		}
	}

	public void deleteById(Integer id) {
		categoryProxy.deleteById(id);
	}

}
