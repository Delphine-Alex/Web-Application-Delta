package com.delta.blog.BlogClient.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.delta.blog.BlogClient.model.Article;
import com.delta.blog.BlogClient.model.Category;
import com.delta.blog.BlogClient.model.Comment;
import com.delta.blog.BlogClient.service.CategoryService;
import com.delta.blog.BlogClient.service.CommentService;

@Controller
@RequestMapping("private")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CommentService commentService;

	@PostMapping("/category")
	public ModelAndView createNewCategory(@ModelAttribute Category category) {
		List<Article> articlesFromCategory = categoryService.getCategoryById(category.getId()).getArticles();
		category.setArticles(articlesFromCategory);

		List<Comment> allComments = commentService.getComments();
		List<Comment> missingComments = new ArrayList<Comment>();
		for (int i = 0; i < allComments.size(); i++) {
			for (int y = 0; y < category.getArticles().size(); y++) {
				if (allComments.get(i).getArticle_id()
						.compareTo(category.getArticles().get(y).getId().toString()) == 0) {
					missingComments.add(allComments.get(i));
				}
			}
		}

		categoryService.addCategory(category);

		for (int i = 0; i < missingComments.size(); i++) {
			commentService.addComment(missingComments.get(i));
		}

		return new ModelAndView("redirect:/public/categories");
	}

	@GetMapping("/newCategory")
	public String newCategoryPage(Model model) {
		model.addAttribute("category", new Category());
		return "newCategory";
	}

	@GetMapping("/deleteCategory/{id}")
	public ModelAndView deleteCategory(@PathVariable(name = "id") Integer id) {
		categoryService.deleteById(id);
		return new ModelAndView("redirect:/public/categories");
	}

	@GetMapping("/updateCategory/{id}")
	public String updateCategory(@PathVariable(name = "id") Integer id, Model model) {
		model.addAttribute("category", categoryService.getCategoryById(id));
		return "updateCategory";
	}
}
