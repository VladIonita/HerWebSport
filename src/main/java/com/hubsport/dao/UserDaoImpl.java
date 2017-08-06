package com.hubsport.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.hubsport.domain.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	// find user by id
	public User findbyid(Integer id) {
		logger.info("id : {}", id);
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(User.class);
		crit.add(Restrictions.eq("id", id));
		User user = (User) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user.getUsername());
		}
		return user;
	}

	// find user by email
	public User findbyemail(String email) {
		logger.info("email : {}", email);
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(User.class);
		crit.add(Restrictions.eq("email", email));
		User user = (User) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user.getUsername());
		}
		return user;
	}

	@Override
	public User findbyusername(String username) {
		logger.info("username : {}", username);
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(User.class);
		crit.add(Restrictions.eq("username", username));
		User user = (User) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user.getUsername());
		}
		return user;
	}

	// find all users
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUsers() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class)
				.addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid
																		// duplicates.
		List<User> users = (List<User>) criteria.list();
		return users;
	}

	// save user
	@Override
	public void save(User user) {
		logger.info("user : {}", user);
		sessionFactory.getCurrentSession().save(user);
	}

	public void update(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

	@Override
	public void deleteId(Integer id) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(User.class);
		crit.add(Restrictions.eq("id", id));
		User user = (User) crit.uniqueResult();
		sessionFactory.getCurrentSession().delete(user);
	}
}