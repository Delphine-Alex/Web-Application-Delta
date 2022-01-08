package com.delta.blog.BlogClient.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delta.blog.BlogClient.model.Comment;
import com.delta.blog.BlogClient.repository.CommentProxy;

@Service
public class CommentService {
	@Autowired
	private CommentProxy commentProxy;

	public Comment getCommentById(Integer id) {
		return commentProxy.getCommentById(id);
	}

	public void addComment(Comment comment) {
		commentProxy.addComment(comment);
	}

	public List<Comment> getComments() {
		return commentProxy.getComments();
	}
}
