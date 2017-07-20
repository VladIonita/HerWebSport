package com.hubsport.service;

import java.util.List;

import com.hubsport.domain.User;

public interface UserService {
	
    User findById(int id);
    
    User findByEmail(String email);
     
    void saveUser(User user);
     
    void updateUser(User user);
     
    void deleteUserByEmail(String email);
 
    List<User> findAllUsers(); 
     
    boolean isUserEmailUnique(Integer id, String email);
}
