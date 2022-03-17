package com.itsupport.serviceimpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsupport.entity.Admin;
import com.itsupport.entity.Ticket;
import com.itsupport.entity.User;
import com.itsupport.repository.TicketRepository;
import com.itsupport.response.TicketResponse;
import com.itsupport.service.AdminService;
import com.itsupport.service.CategoryService;
import com.itsupport.service.PriorityService;
import com.itsupport.service.StatusService;
import com.itsupport.service.Sub_CategoryService;
import com.itsupport.service.TicketService;
import com.itsupport.service.UserService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private AdminService adminService;

	@Autowired
	private UserService userService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private Sub_CategoryService sub_CategoryService;

	@Autowired
	private PriorityService priorityService;

	@Override
	public List<Ticket> getAllTickets() {
		List<Ticket> ticketList = new ArrayList<Ticket>();
		ticketList = ticketRepository.findAll();
		return ticketList;
	}

	@Override
	public String createTicket(Ticket ticket, int userid) {
		String category = null;
		String sub_category = null;
		String priority = null;
		try {

			if (ticket.getSubject().length() <= 10 && ticket.getDescription().length() <= 10)
				return "Please add Proper Description Or Subject...!";
			if (getTicket((int) ticket.getTicket_id()) != null)
				return "Duplicate ticket id";
			if (userService.getUser(userid) == null)
				return "Invalid User Id..!";
			User user = userService.getUser(userid);
			if (user == null)
				return "Invalid User Id...!";
			try {
				category = categoryService.getDescription(Integer.parseInt(ticket.getCategory_id()));
				sub_category = sub_CategoryService.getDescription(Integer.parseInt(ticket.getSub_category_id()),
						Integer.parseInt(ticket.getCategory_id()));
				priority = priorityService.getDescription(Integer.parseInt(ticket.getPriority_id()));
			} catch (Exception e) {

			}
			if(category==null)
				return "Invalid Category Id...! \n1)Hardware\n2)Software\n3)Access Management";
			if (sub_category == null)
				return getMessage("Invalid Sub_Category Id...!");
			if (priority == null)
				return "Invalid Priority Id...!";

			ticket.setPriority_id(priority);
			ticket.setCategory_id(category);
			ticket.setSub_category_id(sub_category);
			ticket.setUser(user);
			ticket.setStatus_id(statusService.getDescription(1));
			ticket.setReported_id(userid);
			LocalDateTime now = LocalDateTime.now();
			ticket.setCreate_datetime(getTimeNow(now));
			ticket.setLast_modified_datetime(getTimeNow(now));
			ticketRepository.save(ticket);
		} catch (Exception e) {

		}
		return "Ticket Created Successfully Created...!\n" + "http://localhost:8080/api/tickets/"
				+ ticket.getTicket_id();
	}

	@Override
	public Ticket getTicket(int ticketid) {
		Ticket ticket = null;
		try {
			ticket = ticketRepository.findById(ticketid).get();
		} catch (Exception e) {

		}
		return ticket;
	}

	@Override
	public Ticket getTicket(int ticketid, int userid) {
		Ticket ticket = null;
		User user =null;
		try {
			user = userService.getUser(userid);
			if (user == null)
				return ticket;
			ticket = getTickets(userid).stream()
					.filter(t -> t.getTicket_id() == ticketid && t.getReported_id() == userid).findFirst().get();
		} catch (Exception e) {

		}
		return ticket;
	}

	@Override
	public List<Ticket> getTickets(String value) {
		List<Ticket> ticketList = new ArrayList<Ticket>();
		try {
			ticketList = ticketRepository.findAll().stream()
					.filter(t -> isPresent(t, value) || !t.getCommentList().stream()
							.noneMatch(c -> c.getMessage().toLowerCase().contains(value.toLowerCase())
									|| (c.getComment_id() + "").contains(value)))
					.collect(Collectors.toList());
			List<Ticket> tList = new ArrayList<Ticket>();
			List<Ticket> cList = new ArrayList<Ticket>();
			// Searching in ticket
			tList = ticketRepository.findAll().stream().filter(t -> t.getAssignee() != null || !isPresent(t, value))
					.collect(Collectors.toList()).stream()
					.filter(a -> a.getAssignee().getName().toLowerCase().contains(value)
							|| a.getAssignee().getEmail().toLowerCase().contains(value.toLowerCase()))
					.collect(Collectors.toList());

			ticketList.addAll(tList);

		} catch (Exception e) {

		}
		return ticketList;
	}

	@Override
	public List<Ticket> getTickets(int userid) {
		List<Ticket> list = new ArrayList<Ticket>();
		try {
			list = ticketRepository.findAll().stream().filter(t -> t.getReported_id() == userid)
					.collect(Collectors.toList());
		} catch (Exception e) {

		}
		return list;
	}

	@Override
	public String setAssignee(int ticketid, int id, Admin admin) {
		String response = null;
		Ticket ticket = null;
		Admin admin1 = null;
		try {
			ticket = getTicket(ticketid);
			admin1 = adminService.getAdmin(admin.getAdmin_id());
			if (ticket == null)
				return "Invalid Ticket Id";
			if (adminService.getAdmin(id) == null || admin1 == null)
				return "Invalid Admin Id";
			admin1 = adminService.getAdmin(admin.getAdmin_id());
			ticket.setAssignee(admin1);
			LocalDateTime now = LocalDateTime.now();
			ticket.setLast_modified_datetime(getTimeNow(now));
			ticketRepository.save(ticket);
		} catch (Exception e) {

		}
		response = "Assignee set Successfully";
		return response;
	}

	@Override
	public String changeStatus(int ticketid, int id, int status_id) {
		Ticket ticket = null;
		Admin admin = null;
		String previous = null;
		String present = null;
		try {
			ticket = getTicket(ticketid);
			if (ticket == null)
				return "Invalid Ticket ID...!";
			previous = ticket.getStatus_id();

			admin = adminService.getAdmin(id);
			if (admin == null)
				return "Invalid Admin ID...!";
			String status = statusService.getDescription(status_id);
			if (status == null)
				return "Invalid Status Id";

			ticket.setStatus_id(statusService.getDescription(status_id));
			LocalDateTime now = LocalDateTime.now();
			ticket.setLast_modified_datetime(getTimeNow(now));
			ticketRepository.save(ticket);
			present = ticket.getStatus_id();

		} catch (Exception e) {

		}
		return "Status Change from " + previous + " to " + present;
	}

	@Override
	public Ticket updateTicket(Ticket ticket) {
		LocalDateTime now = LocalDateTime.now();
		ticket.setLast_modified_datetime(getTimeNow(now));
		return ticketRepository.save(ticket);
	}

	public String getTimeNow(LocalDateTime now) {

		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
		String formatDateTime = now.format(format);
		return formatDateTime;
	}

	public boolean isPresent(Ticket t, String value) {
		return t.getSubject().toLowerCase().contains(value.toLowerCase())
				|| t.getDescription().toLowerCase().contains(value.toLowerCase())
				|| t.getLast_modified_datetime().contains(value) || t.getCreate_datetime().contains(value.toLowerCase())
				|| t.getUser().getEmail().toLowerCase().contains(value.toLowerCase())
				|| t.getCategory_id().toLowerCase().contains(value.toLowerCase())
				|| t.getPriority_id().toLowerCase().contains(value.toLowerCase())
				|| t.getStatus_id().toLowerCase().contains(value.toLowerCase())
				|| t.getSub_category_id().toLowerCase().contains(value.toLowerCase())
				|| (t.getTicket_id() + "").contains(value.toLowerCase());

	}

	public String getMessage(String message) {
		return message + "\n"
				+ "1)Hardware \n \t1)Alloctae Laptop \n\t2)Allocate Hardware \n\t3)Hardware Replacement \n"
				+ "2)Software \n\t4)Software Installation \n\t5)Antivirus \n\t6)Email Password Update \n\t7)Laptop Slowness issue \n\t8)Software Issue \n"
				+ "3)Access Mangement \n\t9)Software access \n\t10)Wifi Access \n\t11)Database Access \n\t12)VPN Access";
	}

	public List<TicketResponse> getList(List<Ticket> ticketList) {
		List<TicketResponse> list = new ArrayList<TicketResponse>();
		for (Ticket t : ticketList) {
			list.add(new TicketResponse(t));
		}
		return list;
	}

	public TicketResponse getTicket(Ticket ticket) {
		return new TicketResponse(ticket);
	}
}
