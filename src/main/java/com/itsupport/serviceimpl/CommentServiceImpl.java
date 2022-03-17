package com.itsupport.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsupport.entity.Admin;
import com.itsupport.entity.Comment;
import com.itsupport.entity.Ticket;
import com.itsupport.entity.User;
import com.itsupport.repository.CommentRepository;
import com.itsupport.service.AdminService;
import com.itsupport.service.CommentService;
import com.itsupport.service.TicketService;
import com.itsupport.service.UserService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private AdminService adminService;

	@Autowired
	private UserService userService;

	@Autowired
	private TicketService ticketService;

	@Override
	public String addAdminComment(int ticketid, int userid, Comment comment) {
		Admin admin = null;
		Ticket ticket = null;
		try {
			admin = adminService.getAdmin(userid);
			if (admin == null)
				return "Invalid Admin Id...!";
			ticket = ticketService.getTicket(ticketid);
			if (ticket == null)
				return "Invalid Ticket Id...!";
			comment.setMessage(admin.getName() + " : " + comment.getMessage());
			comment.setAdmin(admin);
			comment.setTicket(ticketService.updateTicket(ticket));

			commentRepository.save(comment);
		} catch (Exception e) {

		}
		return "Comment added Successfully on Ticket " + ticket.getTicket_id();
	}

	@Override
	public String addUserComment(int ticketid, int userid, Comment comment) {
		User user = null;
		Ticket ticket = null;
		try {
			user = userService.getUser(userid);
			if (user == null)
				return "Invalid User Id...!";
			ticket = ticketService.getTicket(ticketid);
			if (ticket == null)
				return "Invalid Ticket Id...!";

			comment.setMessage(user.getName() + " : " + comment.getMessage());
			comment.setTicket(ticketService.updateTicket(ticket));
			comment.setUser(user);

			commentRepository.save(comment);

		} catch (Exception e) {

		}
		return "Comment added Successfully on Ticket " + ticket.getTicket_id();
	}

}
