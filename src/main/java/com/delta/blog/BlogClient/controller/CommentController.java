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
	@DeleteMapping("/comment/{id}")
	public ModelAndView deleteCommentPage(Comment comment,@PathVariable("id") Integer id) {
		commentService.deleteCommentById(comment,id);
		return new ModelAndView("redirect:/comments");
	}
	// add Update and Delete Comment
}
