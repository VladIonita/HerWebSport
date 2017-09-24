package com.hubsport.dao.impl;


import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hubsport.dao.PasswordTokenDao;
import com.hubsport.domain.PasswordResetToken;

@Repository("passwordTokenDao")
public class PasswordTokenDaoImpl implements PasswordTokenDao {

	static final Logger logger = LoggerFactory.getLogger(PasswordTokenDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public PasswordResetToken findPasswordResetToken(Integer someId) {

		logger.info("id : {}", someId);
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(PasswordResetToken.class);
		crit.add(Restrictions.eq("users.id", someId));
		PasswordResetToken passwordResetToken = (PasswordResetToken) crit.uniqueResult();
		if (passwordResetToken != null) {
			Hibernate.initialize(passwordResetToken.getToken());
		}
		return passwordResetToken;
	}

	// find user by id
	public PasswordResetToken findTokenById(Integer id) {
		logger.info("id : {}", id);
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(PasswordResetToken.class);
		crit.add(Restrictions.eq("id", id));
		PasswordResetToken passwordResetToken = (PasswordResetToken) crit.uniqueResult();
		if (passwordResetToken != null) {
			Hibernate.initialize(passwordResetToken.getToken());
		}
		return passwordResetToken;
		
	}
	
	public PasswordResetToken findPasswordResetTokenByToken(String token) {
		logger.info("token : {}", token);
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(PasswordResetToken.class);
		crit.add(Restrictions.eq("token", token));
		PasswordResetToken passwordResetToken = (PasswordResetToken) crit.uniqueResult();
		if (passwordResetToken != null) {
			Hibernate.initialize(passwordResetToken.getToken());
		}
		return passwordResetToken;
	}

	@Override
	public void save(PasswordResetToken token) {
		sessionFactory.getCurrentSession().save(token);
	}

	public void deleteTokenWithId(Integer id) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(PasswordResetToken.class);
		crit.add(Restrictions.eq("users.id", id));
		PasswordResetToken token = (PasswordResetToken) crit.uniqueResult();
		System.out.println("deleting token with id " + id);
		sessionFactory.getCurrentSession().delete(token);
	}
	

}