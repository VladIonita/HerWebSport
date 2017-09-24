package com.hubsport.service.impl;

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
import com.hubsport.service.UserService;

@Service("userService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordTokenDao passwordTokenDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Users findUserById(Integer id) {
		return userDao.findUserById(id);
	}

	public Users findUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}

	public void deleteUserById(Integer id) {
		userDao.deleteUserById(id);
	}

	public void saveOrUpdateUser(Users user) {
		if (findUserById(user.getId()) == null) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userDao.saveUser(user);
		} else {
			Users entity = userDao.findUserById(user.getId());
			if (entity != null) {
				entity.setFirstName(user.getFirstName());
				entity.setLastName(user.getLastName());
				entity.setEmail(user.getEmail());
				if (!user.getPassword().equalsIgnoreCase("")) {
					entity.setPassword(passwordEncoder.encode(user.getPassword()));
				}
			}
		}
	}

	public void updatePassword(Integer userTokenId, String password) {
		Users user = userDao.findUserById(userTokenId);
		if (user != null) {
			user.setPassword(passwordEncoder.encode(password));
			passwordTokenDao.deleteTokenWithId(userTokenId);
		}
	}

	public boolean isUserEmailUnique(Integer id, String email) {
		Users user = userDao.findUserByEmail(email);
		return (user == null || ((id != null) && (user.getId() == id)));
	}

	public List<Users> findUsersForJson(Integer start, Integer lenght) {
		return userDao.findUsersForJson(start, lenght);
	}

	public Users findUserByResetToken(String token) {
		return passwordTokenDao.findPasswordResetTokenByToken(token).getUsers();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveToken(String token, Users user) {
		Date expiryDate = new Date();
		expiryDate.setTime(Calendar.getInstance().getTimeInMillis() + 1000 * 60 * 60 * 24);
		PasswordResetToken myToken = new PasswordResetToken(token, user, expiryDate);
		if (passwordTokenDao.findPasswordResetToken(myToken.getUsers().getId()) == null) {
			passwordTokenDao.save(myToken);
		}
		passwordTokenDao.deleteTokenWithId(myToken.getUsers().getId());
		passwordTokenDao.save(myToken);

	}

	public boolean validatePasswordResetToken(String token) {
		PasswordResetToken passwordResetToken = passwordTokenDao.findPasswordResetTokenByToken(token);
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

	public Long countGet() {
		return userDao.countUsers();
	}
}