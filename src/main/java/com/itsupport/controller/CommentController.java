package com.itsupport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itsupport.entity.Comment;
import com.itsupport.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private CommentService commentService;

	@PostMapping("/comments/admin")
	public ResponseEntity<?> addAdminComment(@RequestParam("ticketid") int ticketid, @RequestParam("userid") int userid,
			@RequestBody Comment comment) {
		String response = commentService.addAdminComment(ticketid, userid, comment);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping("/comments/user")
	public ResponseEntity<?> addUserComment(@RequestParam("ticketid") int ticketid, @RequestParam("userid") int userid,
			@RequestBody Comment comment) {
		String response = commentService.addUserComment(ticketid, userid, comment);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
}
