package com.hubsport.service;

import java.util.List;

import com.hubsport.domain.User;

public interface UserService {
	
    User findById(int id);
    
    User findByUsername(String username);
    
    User findByEmail(String email);
     
    void saveUser(User user);
     
    void updateUser(User user);
     
    void deleteUserByUsername(String username);
 
    List<User> findAllUsers(); 
     
    boolean isUserUnique(Integer id,String username);
    
    boolean isUserEmailUnique(Integer id,String email);
}