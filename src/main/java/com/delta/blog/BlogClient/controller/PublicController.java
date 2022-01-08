package com.delta.blog.BlogClient.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.delta.blog.BlogClient.model.Article;
import com.delta.blog.BlogClient.model.Category;
import com.delta.blog.BlogClient.model.Comment;
import com.delta.blog.BlogClient.service.ArticleService;
import com.delta.blog.BlogClient.service.CategoryService;
import com.delta.blog.BlogClient.service.CommentService;

@Controller
@RequestMapping("public")
public class PublicController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CommentService commentService;

	@GetMapping("/article/{id}")
	public String articlePage(@PathVariable(name = "id") Integer id, Model model) {
		Article article = articleService.getArticleById(id);

		// Get comments liked with current article
		List<Comment> allComments = commentService.getComments();
		List<Comment> comments = new ArrayList<Comment>();
		for (int i = 0; i < allComments.size(); i++) {
			if (allComments.get(i).getArticle_id().compareTo(Integer.toString(id)) == 0) {
				comments.add(allComments.get(i));
			}
		}
		model.addAttribute("article", article);
		model.addAttribute("comments", comments);
		return "article";
	}

	@GetMapping("/categories")
	public String categoriesPage(Model model, HttpSession session) {
		List<Category> categories = categoryService.getCategories();
		model.addAttribute("categories", categories);
		return "categories";
	}

	@GetMapping("/category/{id}")
	public String categoryPage(@PathVariable(name = "id") Integer id, Model model) {
		Category category = categoryService.getCategoryById(id);
		model.addAttribute("category", category);
		return "category";
	}
}
