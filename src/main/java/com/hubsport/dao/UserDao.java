package com.hubsport.dao;

import java.util.List;

import com.hubsport.domain.User;

public interface UserDao {

    User findById(int id);
    
    User findBUsername(String username);
    
    User findByEmail(String email);
     
    void save(User user);
     
    void deleteByUsername(String username);
     
    List<User> findAllUsers();
}
