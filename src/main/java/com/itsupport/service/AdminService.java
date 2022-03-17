package com.itsupport.service;

import java.util.List;

import com.itsupport.entity.Admin;

public interface AdminService {
		
	List<Admin> adminList();

		Admin createAdmin(Admin admin);

		Admin getAdmin(int id);

		List<Admin> getAdmin(String value);

		String updateAdmin(int id, Admin admin);
	 
		boolean deleteAdmin(int id);

}
