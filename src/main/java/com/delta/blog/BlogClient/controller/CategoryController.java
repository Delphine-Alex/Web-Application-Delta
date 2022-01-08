package com.delta.blog.BlogClient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.delta.blog.BlogClient.model.Category;
import com.delta.blog.BlogClient.service.CategoryService;

@Controller
@RequestMapping("private")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/categories")
	public ModelAndView createNewCategory(@ModelAttribute Category category) {
		categoryService.addCategory(category);
		return new ModelAndView("redirect:/categories");
	}
	
	@GetMapping("/newCategory")
	public String newCategoryPage(Model model) {
		model.addAttribute("category", new Category());
		return "newCategory";
	}
	@DeleteMapping("delete/category/{id}")
	public String deleteCategoryPage(Category category,@PathVariable("id") Integer id) {
		categoryService.deleteCategoryById(category,id);
		return "redirect:/categories";
	}
	
	// add Update and Delete Category
}
