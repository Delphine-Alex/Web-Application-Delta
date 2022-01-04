package com.delta.blog.BlogClient.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.delta.blog.BlogClient.model.Comment;
import com.delta.blog.BlogClient.service.CommentService;


@Controller
@RequestMapping("private")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/comments")
	public ModelAndView createNewComment(@ModelAttribute Comment comment) {
		commentService.addComment(comment);
		return new ModelAndView("redirect:/comments");
	}
	
	@GetMapping("/newComment")
	public String newCommentPage(Model model) {
		model.addAttribute("comment", new Comment());
		return "newComment";
	}
	// add Update and Delete Comment
}
