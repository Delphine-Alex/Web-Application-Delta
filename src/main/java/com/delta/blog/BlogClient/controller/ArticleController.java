package com.delta.blog.BlogClient.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.delta.blog.BlogClient.model.Article;
import com.delta.blog.BlogClient.service.ArticleService;
import com.delta.blog.BlogClient.service.CategoryService;

@Controller
@RequestMapping("private")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CategoryService categoryService;

	@PostMapping("/article")
	public ModelAndView createNewArticle(@RequestParam("category_id") String category_id, @ModelAttribute Article article) {
		
		// Should get the logged API user
		article.setAuthor_name("Jinx");
		article.setUser_id("2");
		
		// Should get params for category id
		article.setCategory_id(category_id);
		
		// Save when the article was made	 
		Date now = new Date(System.currentTimeMillis());
		System.out.println(now);
		article.setDate(now);
		
		System.out.println(article.getAuthor_name());
		System.out.println(article.getUser_id());
		
		articleService.addArticle(article);
		return new ModelAndView("redirect:/public/category/" + category_id);
	}

	@GetMapping("/newArticle")
	public String newArticlePage(@RequestParam("category_id") Integer category_id, Model model) {
		model.addAttribute("article", new Article());
		model.addAttribute("category", categoryService.getCategoryById(category_id));
		return "newArticle";
	}
	// add Update and Delete Article
}
