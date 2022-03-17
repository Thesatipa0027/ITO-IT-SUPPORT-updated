package com.itsupport.service;

import com.itsupport.entity.Comment;


public interface CommentService {
	String addAdminComment(int ticketid, int userid, Comment comment);

	String addUserComment(int ticketid, int userid, Comment comment);
}
