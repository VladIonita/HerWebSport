package com.hubsport.dao;

import com.hubsport.domain.PasswordResetToken;

public interface PasswordTokenDao {

	PasswordResetToken findbyid(Integer id);
	
	PasswordResetToken findbyToken(String token);

	void save(PasswordResetToken token);

}
