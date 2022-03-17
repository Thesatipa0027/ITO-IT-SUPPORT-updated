package com.itsupport.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsupport.entity.Admin;
import com.itsupport.repository.AdminRepository;
import com.itsupport.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public List<Admin> adminList() {
		List<Admin> list = new ArrayList<Admin>();
		list = adminRepository.findAll();
		return list;
	}

	@Override
	public Admin createAdmin(Admin admin) {
		Admin newAdmin = null;
		try {
			if (adminRepository.findAll().stream()
					.anyMatch(a -> a.getEmail().equals(admin.getEmail()) || a.getAdmin_id() == admin.getAdmin_id()))
				return newAdmin;
			newAdmin = adminRepository.save(admin);
		} catch (Exception e) {
			e.printStackTrace();
		}


		return newAdmin;
	}

	@Override
	public Admin getAdmin(int id) {
		Admin admin = null;
		try {
			if (adminRepository.findById(id).isPresent())
			admin = adminRepository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admin;
	}

	@Override
	public List<Admin> getAdmin(String value) {
		List<Admin> adminlist = new ArrayList<>();

		// if (value == null || value.length() == 0)
		// return adminlist;

		try {
			adminlist = adminRepository.findAll().stream()
					.filter(a -> a.getName().toLowerCase().contains(value) || a.getEmail().toLowerCase().contains(value)
							|| (a.getAdmin_id() + " ").toLowerCase().contains(value) || !isTicket(a, value)
							|| !inComments(a, value))
					.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adminlist;
	}

	@Override
	public String updateAdmin(int id, Admin admin) {
		Admin newAdmin = null;

		try {
			newAdmin = adminRepository.findById(id).get();
			if (newAdmin == null)
				return "Admin Not Found...!";
			List<Admin> list = getAdmin(admin.getEmail());
			if (list.size() != 0)
			if (list.get(0).getEmail().equals(admin.getEmail()) && list.get(0).getAdmin_id() != id)
				return "Mail already taken...!";
			if (admin.getName() != null && admin.getEmail() != null
					&& admin.getName().length() != 0 && admin.getEmail().length() != 0) {
				newAdmin.setName(admin.getName());
				newAdmin.setEmail(admin.getEmail());
			} else if (admin.getName() != null && admin.getName().length() != 0)
				newAdmin.setName(admin.getName());
			else if (admin.getEmail() != null && admin.getEmail().length() != 0)
				newAdmin.setEmail(admin.getEmail());
			adminRepository.save(newAdmin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Admin updated successfully...!";
	}

	@Override
	public boolean deleteAdmin(int id) {
		boolean isPresent = true;
		try {
			if (getAdmin(id) != null) {
			adminRepository.deleteById(id);
				isPresent = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isPresent;
	}

	public boolean isTicket(Admin a, String value) {
		return a.getTicketlist().stream()
				.noneMatch(t -> t.getDescription().toLowerCase().contains(value)
						|| t.getSubject().toLowerCase().contains(value)
						|| t.getUser().getName().toLowerCase().contains(value)
						|| t.getUser().getEmail().toLowerCase().contains(value)
						|| t.getCreate_datetime().contains(value) || t.getLast_modified_datetime().contains(value)
						|| (t.getReported_id() + "").toLowerCase().contains(value.toLowerCase())
						|| (t.getStatus_id() + "").contains(value) || (t.getCategory_id() + "").contains(value)
						|| (t.getSub_category_id() + "").toLowerCase().contains(value)
						|| t.getCommentList().stream()
								.noneMatch(c -> c.getMessage().toLowerCase().contains(value.toLowerCase())
										|| (c.getComment_id() + "").contains(value)));
	}

	public boolean inComments(Admin a, String value) {
		return a.getCommentList().stream().noneMatch(c -> c.getMessage().toLowerCase().contains(value.toLowerCase())
				|| (c.getComment_id() + "").contains(value)
				|| c.getUser().getCommentList().stream()
						.noneMatch(uc -> uc.getMessage().toLowerCase().contains(value.toLowerCase())
								|| (uc.getComment_id() + "").contains(value)));
	}
}
