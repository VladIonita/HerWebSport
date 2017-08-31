package com.hubsport.service;

import java.util.List;

import com.hubsport.domain.Users;

public interface UserService {
	
    Users findById(Integer id);
    
    Users findByEmail(String email);
    
    List<Users> findUsers(Integer start, Integer lenght); 
    
    void saveOrUpdate(Users users);
    
    void updatePass(Integer userTokenId, String pass);
    
    void deleteBID(Integer id);
 
    boolean isUserEmailUnique(Integer id,String email);
    
    void saveToken(String token, Users users);
    
    Users findUserByResetToken(String token);
    
    boolean validatePasswordResetToken(String token);
    
	Long countGet();
    
}