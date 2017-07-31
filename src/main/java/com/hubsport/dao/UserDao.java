package com.hubsport.dao;

import java.util.List;

import com.hubsport.domain.User;

public interface UserDao {

	User findbyid(Integer id);
	
	User findbyusername(String username);

	User findbyemail(String email);
	
	List<User> findAllUsers();
	
	void save(User user);
	
	void update(User user);
	
	void deleteId(Integer id);

}
