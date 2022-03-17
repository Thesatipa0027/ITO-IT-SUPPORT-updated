package com.itsupport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itsupport.entity.Admin;
import com.itsupport.response.ResponseModel;
import com.itsupport.service.AdminService;


@RestController
@RequestMapping("/api")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/admins")
	public ResponseEntity<?> getAllAdmins() {
		if (adminService.adminList().isEmpty())
			return new ResponseEntity<>(new ResponseModel("No Data Found...!"), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(adminService.adminList(), HttpStatus.FOUND);
	}

	@PostMapping("/admins")
	public ResponseEntity<?> createAdmin(@RequestBody Admin admin) {
		Admin a = adminService.createAdmin(admin);
		if (a == null)
			return new ResponseEntity<>(new ResponseModel("Admin already exists...!"), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Admin>(a, HttpStatus.CREATED);
	}

	@GetMapping("/admins/{id}")
	public ResponseEntity<?> getAdmin(@PathVariable("id") int id) {
		if (adminService.getAdmin(id) == null)
			return new ResponseEntity<>(new ResponseModel("Invalid Admin Id...!"), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(adminService.getAdmin(id), HttpStatus.FOUND);
	}

	@GetMapping("/admins/")
	public ResponseEntity<?> getAdmin(@RequestParam("value") String value) {
		if (adminService.getAdmin(value).isEmpty())
			return new ResponseEntity<>(new ResponseModel("Not Found...!"), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(adminService.getAdmin(value), HttpStatus.FOUND);
	}

	@PutMapping("/admins/")
	public ResponseEntity<?> updateAdmin(@RequestParam("id") int id, @RequestBody Admin admin) {
//		if (adminService.updateAdmin(id, admin) == null)
//			return new ResponseEntity<>(new ResponseModel("Invalid Admin Id...!"), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(adminService.updateAdmin(id, admin), HttpStatus.OK);
	}

	@DeleteMapping("/admins/{id}")
	public ResponseEntity<?> deleteAdmin(@PathVariable("id") int id) {
		if (adminService.deleteAdmin(id))
			return new ResponseEntity<>(new ResponseModel("Invalid Admin Id...!"), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(new ResponseModel("Deleted Successfully...!"), HttpStatus.OK);
	}
}
