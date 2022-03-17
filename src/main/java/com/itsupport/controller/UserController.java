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

import com.itsupport.entity.User;
import com.itsupport.response.ResponseModel;
import com.itsupport.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers() {
		if (userService.userList().isEmpty())
			return new ResponseEntity<>(new ResponseModel("No Data Found...!"), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(userService.userList(), HttpStatus.FOUND);
	}

	@PostMapping("/users")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		User u = userService.createUser(user);
		if (u == null)
			return new ResponseEntity<>(new ResponseModel("User already exist...!"), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<User>(u, HttpStatus.CREATED);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUser(@PathVariable("id") int id) {
		if (userService.getUser(id) == null)
			return new ResponseEntity<>(new ResponseModel("Not Found...!"), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(userService.getUser(id), HttpStatus.FOUND);
	}

	@GetMapping("/users/")
	public ResponseEntity<?> getUser(@RequestParam("value") String value) {
//		if (userService.getUser(value) == null)
//			return new ResponseEntity<>(new ResponseModel("User Not Found...!"), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(userService.getUser(value), HttpStatus.FOUND);
	}

	@PutMapping("/users/")
	public ResponseEntity<?> updateUser(@RequestParam("id") int id, @RequestBody User user) {
//		if (userService.updateUser(id, user) == null)
//			return new ResponseEntity<>(new ResponseModel("User Not Found...!"), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
		if (userService.deleteUser(id))
			return new ResponseEntity<>(new ResponseModel("User Not Found...!"), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(new ResponseModel("Deleted Successfully...!"), HttpStatus.OK);
	}

}
