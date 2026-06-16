package com.healthcare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Fallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.custom_exceptions.ResourceNotFoundException;
import com.healthcare.entities.User;
import com.healthcare.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // creates a parameterized ctor with final fields
public class UserServiceImpl implements UserService {
	// dependency - dao layer i/f - constr based D.I
	private final UserRepository userRepo;

//	@Autowired
//	public UserServiceImpl(UserRepository userRepo) {
//		super();
//		this.userRepo = userRepo;
//	}
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	@Transactional
	public String addUser(User newUser) {
		User persistentUser = userRepo.save(newUser);
		return "User added with ID " + persistentUser.getId();
	}

	@Override
	@Transactional
	public String deleteUserDetails(Long userId) {
		if (userRepo.existsById(userId)) {
			userRepo.deleteById(userId);
			return "User details deleted....";
		}
		return "Deletion failed - invalid user id";
	}

	@Override
	public User getUserDetails(Long uid) {
		// TODO Auto-generated method stub
		return userRepo.findById(uid).orElseThrow(() -> new ResourceNotFoundException("Invalid user id !!!!"));
	}

	@Override
	@Transactional(readOnly = false)
	public String updateUser(Long userId, User user) {
		User existingUser = getUserDetails(userId);
		// existingUser - persistent
		// setter - password, reg amount , phone
		existingUser.setPassword(user.getPassword());
		existingUser.setRegAmount(user.getRegAmount());
		existingUser.setPhone(user.getPhone());
		return "User details updated....";
	}

}
