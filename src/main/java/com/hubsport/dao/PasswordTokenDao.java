package com.hubsport.dao;

import java.util.Date;

import com.hubsport.domain.PasswordResetToken;

public interface PasswordTokenDao {
	
	PasswordResetToken findPasswordResetToken(Integer id);

	PasswordResetToken findTokenById(Integer id);
	
	PasswordResetToken findPasswordResetTokenByToken(String token);
	
	void deleteTokenWithId(Integer id);
	
	void save(PasswordResetToken token);

}
