package com.healthcare.dao;

import java.time.LocalDate;
import java.util.List;

import com.healthcare.dtos.UserDTO;
import com.healthcare.entities.User;
import com.healthcare.entities.UserRole;

public interface UserDao {
//add new user
	String registerUser(User user);

	User getUserDetailsById(Long userId);

	List<User> getAllUsers();

	List<User> getUsersByDateAndRole(UserRole valueOf, LocalDate localDate);

	User authenticateUser(String email1, String password1);

	List<String> getUsersLastNamesByRole(UserRole role1);

	List<UserDTO> getSelectedDetailsByRole(UserRole valueOf);

	String applyDiscountByRole(UserRole userRole,int discount);

	String deleteUserDetailsById(Long userId);
}
