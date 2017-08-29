package com.hubsport.dao;

import java.util.Date;

import com.hubsport.domain.PasswordResetToken;

public interface PasswordTokenDao {
	
	PasswordResetToken findPasswordResetToken(Integer id);

	PasswordResetToken findTokenById(Integer id);
	
	PasswordResetToken findbyToken(String token);
	
	void deleteId(Integer id);
	
	void save(PasswordResetToken token);

}
