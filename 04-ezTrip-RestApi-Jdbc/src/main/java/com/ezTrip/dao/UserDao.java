package com.ezTrip.dao;

import java.util.Optional;

import com.ezTrip.entity.User;
import com.ezTrip.entity.UserPassword;

public interface UserDao {
	
	public int saveUserDetails(User user);
	public User fndByEmail(String email);
	//public Optional<UserPassword> findByEmail(String email);
	//public Optional<UserPassword> findByEmail(String email);

}
