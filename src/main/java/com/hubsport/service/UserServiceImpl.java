package com.hubsport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hubsport.dao.PasswordTokenDao;
import com.hubsport.dao.UserDao;
import com.hubsport.domain.PasswordResetToken;
import com.hubsport.domain.Users;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordTokenDao passwordTokenDao;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Users findById(Integer id) {
		return userDao.findbyid(id);
	}
	
	public Users findByEmail(String email) {
		Users users = userDao.findbyemail(email);
		return users;
	}
	
	@Override
	public Users findByUsername(String username) {
		Users users = userDao.findbyusername(username);
		return users;
	}

	public void saveUser(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        userDao.save(users);
    }
	
	public void updateUser(Users users) {
        Users entity = userDao.findbyid(users.getId());
        if(entity!=null){
            entity.setId(users.getId());
            if(!users.getPassword().equals(entity.getPassword())){
                entity.setPassword(passwordEncoder.encode(users.getPassword()));
            }
            entity.setFirstName(users.getFirstName());
            entity.setLastName(users.getLastName());
            entity.setEmail(users.getEmail());
        }
    }

	public void deleteBID(Integer id) {
		userDao.deleteId(id);
	}
	
	@Override
	public boolean isUserEmailUnique(Integer id, String email) {
		Users users = userDao.findbyemail(email);
		return ( users == null || ((id != null) && (users.getId() == id)));
	}

	@Override
	public boolean isUserUnique(Integer id, String username) {
		Users users = userDao.findbyusername(username);
		return ( users == null || ((id != null) && (users.getId() == id)));
	}

	@Override
	public List<Users> findAllUsers() {
		return userDao.findAllUsers();
	}
	
	@Override
	public Users findUserByResetToken(String token) {
		Users users = userDao.findbyid(passwordTokenDao.findbyToken(token).getId());
		return users;
	}

	@Override
	public void saveToken( String token, Users users) {
		PasswordResetToken myToken = new PasswordResetToken(token, users);
		passwordTokenDao.save(myToken);		
	}
}