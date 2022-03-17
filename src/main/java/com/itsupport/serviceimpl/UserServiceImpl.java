package com.itsupport.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsupport.entity.User;
import com.itsupport.repository.UserRepository;
import com.itsupport.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> userList() {
		List<User> list = new ArrayList<User>();
			list = userRepository.findAll();
		return list;
	}

	@Override
	public User createUser(User user) {
		User newUser = null;
		try {
			if (userRepository.findAll().stream()
					.anyMatch(u -> u.getEmail().equals(user.getEmail()) || u.getUser_id() == user.getUser_id()))
				return newUser;
		} catch (Exception e) {
			e.printStackTrace();
		}
		newUser = userRepository.save(user);
		return newUser;
	}

	@Override
	public User getUser(int id) {
		User user = null;
		try {
			if (userRepository.findById(id).isPresent())
			user = userRepository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> getUser(String value) {
		List<User> userList = null ;
		try {
			userList = userRepository.findAll().stream()
					.filter(u -> u.getName().toLowerCase().contains(value.toLowerCase())
							|| u.getEmail().toLowerCase().contains(value.toLowerCase())
							|| (u.getUser_id() + "").contains(value))
					.collect(Collectors.toList());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public String updateUser(int id, User user) {
		User newUser = null;
		try {

			newUser = getUser(id);
			if (newUser == null)
				return "User Not Found...!";

			List<User> list = getUser(user.getEmail());
			if (list.size() != 0)
			if (list.get(0).getEmail().equals(user.getEmail()) && list.get(0).getUser_id() != id)
				return "Mail already taken...!";

		if (user.getEmail() != null && user.getName() != null && user.getName().length() != 0
				&& user.getEmail().length() != 0) {
				newUser.setEmail(user.getEmail());
				newUser.setName(user.getName());
			} else if (user.getEmail().length() != 0)
				newUser.setEmail(user.getEmail());
			else if (user.getName() != null && user.getName().length() != 0)
				newUser.setName(user.getName());
			userRepository.save(newUser);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "User Updated Successfully...!";
	}

	@Override
	public boolean deleteUser(int id) {
		boolean isPresent = true;
		try {
			if (getUser(id) != null) {
			userRepository.deleteById(id);
				isPresent = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isPresent;
	}

}
