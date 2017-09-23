package com.hubsport.dao;

import java.util.List;

import com.hubsport.domain.Users;

public interface UserDao {

	Users findUserById(Integer id);

	Users findUserByEmail(String email);

	void saveUser(Users users);

	void updateUser(Users users);

	void deleteUserById(Integer id);

	Long countUsers();

	List<Users> findUsersForJson(Integer start, Integer lenght);

}
