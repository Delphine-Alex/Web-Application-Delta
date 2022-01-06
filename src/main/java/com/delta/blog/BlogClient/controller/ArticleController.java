package com.delta.blog.BlogClient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.delta.blog.BlogClient.model.Article;
import com.delta.blog.BlogClient.service.ArticleService;

@Controller
@RequestMapping("private")
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	@PostMapping("/articles")
	public ModelAndView createNewArticle(@ModelAttribute Article article) {
		articleService.addArticle(article);
		return new ModelAndView("redirect:/articles");
	}

	@GetMapping("/newArticle")
	public String newArticlePage(Model model) {
		model.addAttribute("article", new Article());
		return "newArticle";
	}
	// add Update and Delete Article
}
