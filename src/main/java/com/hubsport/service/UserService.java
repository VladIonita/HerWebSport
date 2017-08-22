package com.hubsport.service;

import java.util.List;

import com.hubsport.domain.Users;

public interface UserService {
	
    Users findById(Integer id);
    
    Users findByEmail(String email);
    
    List<Users> findAllUsers(); 
    
//    void saveUser(Users users);
//    
//    void updateUser(Users users);

    void saveOrUpdate(Users users);
    
    void deleteBID(Integer id);
 
    boolean isUserEmailUnique(Integer id,String email);
    
    void saveToken(String token, Users users);
    
    Users findUserByResetToken(String token);
    
}