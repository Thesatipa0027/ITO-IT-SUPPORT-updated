package com.itsupport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itsupport.entity.Admin;
import com.itsupport.entity.Ticket;
import com.itsupport.response.ResponseModel;
import com.itsupport.serviceimpl.TicketServiceImpl;

@RestController
@RequestMapping("/api")
public class TicketController {

	@Autowired
	private TicketServiceImpl ticketService;

	@GetMapping("/tickets")
	public ResponseEntity<?> getAllTickets() {
		List<Ticket> list = ticketService.getAllTickets();
		if (list.isEmpty())
			return new ResponseEntity<>(new ResponseModel("No Data Found...!"), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(ticketService.getList(list), HttpStatus.FOUND);
	}

	@PostMapping("/tickets")
	public ResponseEntity<?> createTicket(@RequestBody Ticket ticket, @RequestParam("userid") int userid) {
		String response = ticketService.createTicket(ticket, userid);
//		if (s == null)
//			return new ResponseEntity<>(new ResponseModel("Ticket already exist...!"), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/tickets/{ticketid}")
	public ResponseEntity<?> getTicket(@PathVariable("ticketid") int ticketid) {
		Ticket t = ticketService.getTicket(ticketid);
		if (t == null)
			return new ResponseEntity<>(new ResponseModel("No Data Found...!"), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(t, HttpStatus.CREATED);
	}

	@GetMapping("/tickets/")
	public ResponseEntity<?> getTickets(@RequestParam("userid") int userid) {
		List<Ticket> list = ticketService.getTickets(userid);
		if (list.isEmpty())
			return new ResponseEntity<>(new ResponseModel("No Data Found...!"), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(ticketService.getList(list), HttpStatus.CREATED);
	}

	@GetMapping("/tickets/search")
	public ResponseEntity<?> getTickets(@RequestParam("value") String value) {
		List<Ticket> list = ticketService.getTickets(value);
		if (list.isEmpty())
			return new ResponseEntity<>(new ResponseModel("No Data Found...!"), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(ticketService.getList(list), HttpStatus.CREATED);
	}

	@PutMapping("/tickets/{ticketid}/")
	public ResponseEntity<?> setAssignee(@PathVariable("ticketid") int ticketid, @RequestParam("adminid") int adminid,
			@RequestBody Admin admin) {
		String response = ticketService.setAssignee(ticketid, adminid, admin);
		if (response.equals("Invalid Ticket Id") || response.equals("Invalid Admin Id"))
			return new ResponseEntity<>(new ResponseModel(response), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/tickets/")
	public ResponseEntity<?> changeStatus(@RequestParam("ticketid") int ticketid, @RequestParam("adminid") int adminid,
			@RequestParam("statusid") int statusid) {
		String reponse = ticketService.changeStatus(ticketid, adminid, statusid);
		if (reponse == null)
			return new ResponseEntity<>(new ResponseModel("Invalid Ticket Id or Admin Id...!"), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(reponse, HttpStatus.CREATED);
	}
}
