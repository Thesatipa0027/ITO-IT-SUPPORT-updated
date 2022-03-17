package com.itsupport.service;

import java.util.List;

import com.itsupport.entity.User;

public interface UserService {

	List<User> userList();

	User createUser(User user);

	User getUser(int id);

	List<User> getUser(String value);

	String updateUser(int id, User user);

	boolean deleteUser(int id);
}
