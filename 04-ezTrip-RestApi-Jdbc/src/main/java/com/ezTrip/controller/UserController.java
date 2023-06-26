package com.ezTrip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezTrip.dao.UserDao;
import com.ezTrip.entity.User;
import com.ezTrip.entity.UserPassword;

@RestController
@RequestMapping("/ezTrip")
public class UserController {
	
	@Autowired
	public UserDao userDao;
	
	// User Registration
	@PostMapping("/user/register")
	public ResponseEntity<String> createUser(@RequestBody User user){
		userDao.saveUserDetails(user);
		return new ResponseEntity<>("Registration Successful", HttpStatus.CREATED);
	}
	
	// User sign-in
	@PostMapping("/user/login")
	public ResponseEntity<String> login(@RequestBody UserPassword userPassword){
		User user = userDao.fndByEmail(userPassword.getEmail());
		//System.out.println(user.getPassword());
		//System.out.println(userPassword.getPassword());
		if(user.getPassword() != null && user.getPassword().equals(userPassword.getPassword())) {
				return ResponseEntity.ok("Login successful");
			}
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
	}
	
	// Get profile 
	@GetMapping("/user/{email}")
	public ResponseEntity<User> profilePage(@PathVariable("email") String email){
		User user = userDao.fndByEmail(email);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	
	
	
	
	
	

}
