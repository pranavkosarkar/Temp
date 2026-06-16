package com.healthcare.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.entities.User;
import com.healthcare.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController // =@Controller : cls level + @ResponseBody - ret type of req handling methods
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	 //dependency - service layer interface
	private final UserService userService;
	/*
	 * Desc - Get all users
	 * URI - /users
	 * Method - GET
	 * Payload - none
	 * Resp - List<User>
	 */
	@GetMapping
	public List<User> getAllUsers() {
		System.out.println("in get all users");
		return userService.getAllUsers();
	}
	/*
	 * Desc - Add new user
	 * URI - /users
	 * Method - POST
	 * Payload - user details (JSON)
	 * Resp - message
	 */
	@PostMapping
	public String addNewUser(@RequestBody User newUser)
	{
		System.out.println("in add user "+newUser);
		return userService.addUser(newUser);
	}
	/*
	 * Desc - Delete Existing  user
	 * URI - /users/{userId}
	 * Method - DELETE
	 * URI Template variable - user id
	 * Resp - message
	 */
	@DeleteMapping("/{userId}")
	public String deleteUserDetails(@PathVariable Long userId)
	{
		System.out.println("in delete user "+userId);
		return userService.deleteUserDetails(userId);
	}
	
	
	/*
	 * Desc - Get  Existing  user details by id
	 * URI - /users/{userId}
	 * Method - GET
	 * URI Template variable - user id
	 * Resp - User
	 */
	@GetMapping("/{uid}")
	public /*@ResponseBody*/ User getUserDetails(@PathVariable Long uid)
	{
		System.out.println("in get user details "+uid);
		return userService.getUserDetails(uid);
	}
	/*
	 * Desc - Update  Existing  user details
	 * URI - /users/{userId}
	 * Method - PUT
	 * Payload - updated user details (request body) 
	 * Resp - message
	 */
	@PutMapping("/{userId}")
	public String updateUserDetails(@PathVariable Long userId,@RequestBody User user)
	{
		System.out.println("in update "+userId+" "+user);
		return userService.updateUser(userId,user);
	}

}
