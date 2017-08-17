package com.hubsport.service;

import java.util.List;

import com.hubsport.domain.Users;

public interface UserService {
	
    Users findById(Integer id);
    
    Users findByEmail(String email);
    
    Users findByUsername(String username);
    
    List<Users> findAllUsers(); 
    
    void saveUser(Users users);
    
    void updateUser(Users users);
     
    void deleteBID(Integer id);
 
    boolean isUserUnique(Integer id,String username);
    
    boolean isUserEmailUnique(Integer id,String email);
}