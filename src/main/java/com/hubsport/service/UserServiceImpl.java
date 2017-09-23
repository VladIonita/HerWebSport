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
		return userDao.findUserById(id);
	}

	public Users findByEmail(String email) {
		Users users = userDao.findUserByEmail(email);
		return users;
	}

	public void deleteBID(Integer id) {
		userDao.deleteUserById(id);
	}

	@Override
	public void saveOrUpdate(Users users) {
		if (findById(users.getId()) == null) {
			users.setPassword(passwordEncoder.encode(users.getPassword()));
			userDao.saveUser(users);
		} else {
			Users entity = userDao.findUserById(users.getId());
			if (entity != null) {
				entity.setFirstName(users.getFirstName());
				entity.setLastName(users.getLastName());
				entity.setEmail(users.getEmail());
				if (!users.getPassword().equalsIgnoreCase("")) {
					entity.setPassword(passwordEncoder.encode(users.getPassword()));
				}
			}
		}
	}

	public void updatePass(Integer userTokenId, String pass) {

		
		Users entity = userDao.findUserById(userTokenId);
		if (entity != null) {
			entity.setPassword(passwordEncoder.encode(pass));
			passwordTokenDao.deleteId(userTokenId);
		}
	}

	@Override
	public boolean isUserEmailUnique(Integer id, String email) {
		Users users = userDao.findUserByEmail(email);
		return (users == null || ((id != null) && (users.getId() == id)));
	}

	@Override
	public List<Users> findUsers(Integer start, Integer lenght) {
		return userDao.findUsersForJson(start, lenght);
	}

	@Override
	public Users findUserByResetToken(String token) {
		Users users = passwordTokenDao.findbyToken(token).getUsers();
		return users;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveToken(String token, Users users) {
		Date expiryDate = new Date();
		expiryDate.setTime(Calendar.getInstance().getTimeInMillis() + 1000 * 60 * 60 * 24);
		PasswordResetToken myToken = new PasswordResetToken(token, users, expiryDate);
		if (passwordTokenDao.findPasswordResetToken(myToken.getUsers().getId())==null) {
			
			passwordTokenDao.save(myToken);
		}
		passwordTokenDao.deleteId(myToken.getUsers().getId());
		passwordTokenDao.save(myToken);

	}

	@Override
	public boolean validatePasswordResetToken(String token) {
		PasswordResetToken passwordResetToken = passwordTokenDao.findbyToken(token);
		if (passwordResetToken == null) {
			return false;
		} else {
			if (passwordResetToken.getExpiryDate().after(Calendar.getInstance().getTime())) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	@Override
	public Long countGet() {
		return userDao.countUsers();
	}
}