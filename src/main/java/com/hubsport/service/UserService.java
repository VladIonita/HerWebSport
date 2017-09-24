package com.hubsport.service;

import java.util.List;

import com.hubsport.domain.Users;

public interface UserService {
	
    Users findUserById(Integer id);
    
    Users findUserByEmail(String email);
    
    List<Users> findUsersForJson(Integer start, Integer lenght); 
    
    void saveOrUpdateUser(Users users);
    
    void updatePassword(Integer userTokenId, String password);
    
    void deleteUserById(Integer id);
 
    boolean isUserEmailUnique(Integer id,String email);
    
	void saveToken(String token, Users user);
    
    Users findUserByResetToken(String token);
    
    boolean validatePasswordResetToken(String token);
    
	Long countGet();
    
}