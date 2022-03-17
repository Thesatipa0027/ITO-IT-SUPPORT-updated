package com.itsupport.response;

import com.itsupport.entity.Ticket;

public class TicketResponse {
	private long ticket_id;

	private String category;

	private String sub_category;

	private String subject;

	private String priority;

	private String status;

	private String assignee;

	private String url;

	public TicketResponse() {
		super();
	}

	public TicketResponse(Ticket ticket) {
		this.ticket_id = ticket.getTicket_id();
		this.category = ticket.getCategory_id();
		this.sub_category = ticket.getSub_category_id();
		this.subject = ticket.getSubject();
		this.priority = ticket.getPriority_id();
		this.status = ticket.getStatus_id();
		if (ticket.getAssignee() != null)
			this.assignee = ticket.getAssignee().getName();
		this.url = "http://localhost:8080/api/tickets/" + ticket.getTicket_id();
	}

	public long getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(long ticket_id) {
		this.ticket_id = ticket_id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSub_category() {
		return sub_category;
	}

	public void setSub_category(String sub_category) {
		this.sub_category = sub_category;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
