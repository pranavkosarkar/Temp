package com.healthcare.service;

import java.util.List;

import com.healthcare.entities.User;

public interface UserService {
	List<User> getAllUsers();

	String addUser(User newUser);

	String deleteUserDetails(Long userId);

	User getUserDetails(Long uid);

	String updateUser(Long userId, User user);
}
