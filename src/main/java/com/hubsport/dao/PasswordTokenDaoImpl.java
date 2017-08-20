package com.hubsport.dao;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hubsport.domain.PasswordResetToken;
import com.hubsport.domain.Users;

@Repository("passwordTokenDao")
public class PasswordTokenDaoImpl implements PasswordTokenDao {

	static final Logger logger = LoggerFactory.getLogger(PasswordTokenDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	// find user by id
	public PasswordResetToken findbyid(Integer id) {
		logger.info("id : {}", id);
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(PasswordResetToken.class);
		crit.add(Restrictions.eq("id", id));
		PasswordResetToken passwordResetToken = (PasswordResetToken) crit.uniqueResult();
		if (passwordResetToken != null) {
			Hibernate.initialize(passwordResetToken.getToken());
		}
		return passwordResetToken;
	}
	
	public PasswordResetToken findbyToken(String token) {
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

}