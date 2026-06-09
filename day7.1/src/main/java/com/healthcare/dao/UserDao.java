package com.healthcare.dao;

import com.healthcare.entities.User;

public interface UserDao {
//add new user
	String registerUser(User user);

	User getUserDetailsById(Long userId);
}
