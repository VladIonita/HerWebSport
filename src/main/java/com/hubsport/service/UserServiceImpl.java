package com.hubsport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

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
		return userDao.findbyid(id); //aici e prob
		
	}

	public User findByEmail(String email) {
		User user = userDao.findbyemail(email);
		return user;
	}
	
	@Override
	public User findByUsername(String username) {
		User user = userDao.findbyusername(username);
		return user;
	}

	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.save(user);
	}

	public void updateUser(User user) {
		User entity = userDao.findbyusername(user.getUsername());
		if(entity!=null) {
			entity.setUsername(user.getUsername());
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
	public boolean isUserEmailUnique(Integer id, String email) {
		User user = userDao.findbyemail(email);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}



	@Override
	public boolean isUserUnique(Integer id, String username) {
		User user = userDao.findbyusername(username);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}

}