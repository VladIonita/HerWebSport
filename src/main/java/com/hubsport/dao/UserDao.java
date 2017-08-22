package com.hubsport.dao;

import java.util.List;

import com.hubsport.domain.Users;

public interface UserDao {

	Users findbyid(Integer id);
	
	Users findbyemail(String email);
	
	List<Users> findAllUsers();
	
	void save(Users users);
	
	void update(Users users);
	
	void deleteId(Integer id);

}
