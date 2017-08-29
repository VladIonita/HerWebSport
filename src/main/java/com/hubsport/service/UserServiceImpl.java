package com.hubsport.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hubsport.dao.PasswordTokenDao;
import com.hubsport.dao.UserDao;
import com.hubsport.domain.PasswordResetToken;
import com.hubsport.domain.Users;

@Service("userService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
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

	public void deleteBID(Integer id) {
		userDao.deleteId(id);
	}

	@Override
	public void saveOrUpdate(Users users) {
		if (findById(users.getId()) == null) {
			users.setPassword(passwordEncoder.encode(users.getPassword()));
			userDao.save(users);
		} else {
			Users entity = userDao.findbyid(users.getId());
			if (entity != null) {
				entity.setFirstName(users.getFirstName());
				entity.setLastName(users.getLastName());
				entity.setEmail(users.getEmail());
				System.out.println(users.getPassword());
				if (!users.getPassword().equalsIgnoreCase("")) {
					entity.setPassword(passwordEncoder.encode(users.getPassword()));
				}
			}
		}

	}

	@Override
	public boolean isUserEmailUnique(Integer id, String email) {
		Users users = userDao.findbyemail(email);
		return (users == null || ((id != null) && (users.getId() == id)));
	}

	@Override
	public List<Users> findUsers(Integer start, Integer lenght) {
		return userDao.findUsers(start, lenght);
	}

	@Override
	public Users findUserByResetToken(String token) {
		Users users = userDao.findbyid(passwordTokenDao.findbyToken(token).getId());
		return users;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveToken(String token, Users users) {
		Date expiryDate = new Date();
		expiryDate.setTime(Calendar.getInstance().getTimeInMillis() + 1000 * 60 * 60 * 24);
		System.out.println(users.getId());
		PasswordResetToken myToken = new PasswordResetToken(token, users, expiryDate);
		if(passwordTokenDao.findPasswordResetToken(myToken.getUsers().getId()).equals(null)) {
			passwordTokenDao.save(myToken);
		}
		System.out.println("tokenul exista ");
		passwordTokenDao.deleteId(myToken.getUsers().getId());
		passwordTokenDao.save(myToken);

	}
	
	@Override
	public boolean validatePasswordResetToken(String token) {
		PasswordResetToken passwordResetToken = passwordTokenDao.findbyToken(token);
		
		if(!passwordResetToken.equals(null)) {
			System.out.println("tokenul exista ");
			if(passwordResetToken.getExpiryDate().after(Calendar.getInstance().getTime()) ) {
				System.out.println("tokenul se incadreaza in limita de timp ");
				return true;
			}
		}
		System.out.println("tokenul nu exista ");
		return false;
	}

	@Override
	public Long countGet() {
		return userDao.countUsers();
	}
}