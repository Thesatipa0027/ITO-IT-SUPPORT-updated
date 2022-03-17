package com.itsupport.service;

import java.util.List;

import com.itsupport.entity.Admin;
import com.itsupport.entity.Ticket;

public interface TicketService {

	List<Ticket> getAllTickets();

	String createTicket(Ticket ticket, int userid);

	Ticket getTicket(int ticketid);

	Ticket getTicket(int ticketid, int userid);

	public List<Ticket> getTickets(int userid);

	List<Ticket> getTickets(String value);

	String setAssignee(int ticketid, int adminid, Admin admin);

	String changeStatus(int ticketid, int adminid, int status_id);

	Ticket updateTicket(Ticket ticket);
}
