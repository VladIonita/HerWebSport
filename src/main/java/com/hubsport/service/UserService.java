package com.hubsport.service;

import java.util.List;

import com.hubsport.domain.User;

public interface UserService {
	
    User findById(int id);
    
    User findByEmail(String email);
    
    User findByUsername(String username);
    
    List<User> findAllUsers(); 
    
    void saveOrUpdate(User user);
     
    void delete(Integer id);
 
    boolean isUserUnique(Integer id,String username);
    
    boolean isUserEmailUnique(Integer id,String email);
}