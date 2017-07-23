package com.hubsport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hubsport.dao.UserDao;
import com.hubsport.domain.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User findById(int id) {
		return userDao.findById(id);
	}

	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}
	
	@Override
	public User findByUsername(String username) {
		return userDao.findBUsername(username);
	}

	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.save(user);
	}

	public void updateUser(User user) {
		User entity = userDao.findById(user.getId());
		if(entity!=null) {
			entity.setId(user.getId());
			if(!user.getPassword().equals(entity.getPassword())) {
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
		}
	}

	public void deleteUserByUsername(String username) {
		userDao.deleteByUsername(username);
	}

	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

	
	@Override
	public boolean isUserEmailUnique(String email) {
		User user = userDao.findByEmail(email);
		return (user == null);
	}



	@Override
	public boolean isUserUnique(String username) {
        User user = userDao.findBUsername(username);
		return ( user == null);
	}


}
