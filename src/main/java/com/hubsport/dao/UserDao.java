package com.hubsport.dao;

import java.util.List;

import com.hubsport.domain.Users;

public interface UserDao {

	Users findbyid(Integer id);
	
	Users findbyemail(String email);
	
	
	void save(Users users);
	
	void update(Users users);
	
	void deleteId(Integer id);
	
	Long countUsers();

	List<Users> findUsers(Integer start, Integer lenght);

}
