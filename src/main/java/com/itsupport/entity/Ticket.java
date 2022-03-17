package com.itsupport.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Ticket implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_genrator")
	@SequenceGenerator(name = "ticket_genrator", sequenceName = "ticket_seq")
	private int ticket_id;

	private String category_id;

	private String sub_category_id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assignee_id", referencedColumnName = "admin_id")
//	@JsonIgnore
	private Admin assignee;

	private int reported_id;

	private String subject;

	@Lob
	private String description;

	private String status_id;

	private String priority_id;

	private String create_datetime;

	@Column(name = "last_modified")
	private String last_modified_datetime;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@JsonIgnore
	private User user;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ticket")
//	@JsonIgnore
	private List<Comment> commentList;

	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ticket(String category_id, String sub_category_id, String subject, String description, String priority_id) {
		super();
		this.category_id = category_id;
		this.sub_category_id = sub_category_id;
		this.subject = subject;
		this.description = description;
		this.priority_id = priority_id;
	}

	public long getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public String getSub_category_id() {
		return sub_category_id;
	}

	public void setSub_category_id(String sub_category_id) {
		this.sub_category_id = sub_category_id;
	}

	public Admin getAssignee() {
		return assignee;
	}

	public void setAssignee(Admin assignee) {
		this.assignee = assignee;
	}

	public int getReported_id() {
		return reported_id;
	}

	public void setReported_id(int reported_id) {
		this.reported_id = reported_id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus_id() {
		return status_id;
	}

	public void setStatus_id(String status_id) {
		this.status_id = status_id;
	}

	public String getPriority_id() {
		return priority_id;
	}

	public void setPriority_id(String priority_id) {
		this.priority_id = priority_id;
	}

	public String getCreate_datetime() {
		return create_datetime;
	}

	public void setCreate_datetime(String create_datetime) {
		this.create_datetime = create_datetime;
	}

	public String getLast_modified_datetime() {
		return last_modified_datetime;
	}

	public void setLast_modified_datetime(String last_modified_datetime) {
		this.last_modified_datetime = last_modified_datetime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}


}
