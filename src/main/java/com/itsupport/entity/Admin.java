package com.itsupport.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin_team")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Admin implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_generator")
	@SequenceGenerator(name = "admin_generator", sequenceName = "admin_seq", initialValue = 4)
	private int admin_id;

	private String name;

	private String email;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "assignee", cascade = { CascadeType.ALL })
	@JsonBackReference
	private List<Ticket> ticketlist;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "admin", cascade = { CascadeType.ALL })
	@JsonIgnore
	private List<Comment> commentList;

	public Admin() {
		super();
	}

	public Admin(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public Admin(int admin_id) {
		super();
		this.admin_id = admin_id;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Ticket> getTicketlist() {
		return ticketlist;
	}

	public void setTicketlist(List<Ticket> ticketlist) {
		this.ticketlist = ticketlist;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

}
