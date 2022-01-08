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
import com.delta.blog.BlogClient.model.Comment;
import com.delta.blog.BlogClient.service.ArticleService;
import com.delta.blog.BlogClient.service.CommentService;
import com.delta.blog.BlogClient.service.LoginService;

@Controller
@RequestMapping("private")
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private LoginService loginService;

	@Autowired
	private ArticleService articleService;

	@PostMapping("/comment")
	public ModelAndView createNewComment(@RequestParam("article_id") String article_id,
			@ModelAttribute Comment comment) {
		comment.setAuthor_name(loginService.getCurrentUsername());
		comment.setUser_id(loginService.getCurrentUser_Id().toString());

		Date now = new Date(System.currentTimeMillis());
		System.out.println(now);
		comment.setDate(now);

		comment.setArticle_id(article_id);

		commentService.addComment(comment);

		return new ModelAndView("redirect:/public/article/" + comment.getArticle_id());
	}

	@GetMapping("/newComment")
	public String newCommentPage(@RequestParam("article_id") Integer article_id, Model model) {
		model.addAttribute("comment", new Comment());
		model.addAttribute("article", articleService.getArticleById(article_id));
		return "newComment";
	}
}
