package com.ezTrip.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ezTrip.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	private static final String INSERT_USER_QUERY="INSERT INTO USER(firstName,lastName,email,birthDay,mobileNumber,gender,password) values(?,?,?,?,?,?,?)";
	private static final String SELECT_USER_BY_EMAIL_QUERY="SELECT * FROM user WHERE email=?";


	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int saveUserDetails(User user) {
		return jdbcTemplate.update(INSERT_USER_QUERY, user.getFirstName(), 
													  user.getLastName(), 
													  user.getEmail(), 
													  user.getBirthDay(), 
													  user.getMobileNumber(), 
													  user.getGender(),
													  user.getPassword()
													  );
	}

	@Override
	public User fndByEmail(String email) {
		try {
			User user = jdbcTemplate.queryForObject(SELECT_USER_BY_EMAIL_QUERY, BeanPropertyRowMapper.newInstance(User.class), email);
			return user;
		} catch(IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}

	
	/*@Override
	public Optional<UserPassword> findByEmail(String email) {
			return jdbcTemplate.queryForObject(SELECT_USER_BY_EMAIL_QUERY, new Object[]{email}, (rs, rowNum) ->
            Optional.of(new UserPassword())).stream().findFirst();	
	}*/
	
	
	
	
	
	
	
	
	

}
