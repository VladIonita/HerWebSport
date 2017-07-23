 package com.hubsport.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hubsport.domain.User;

//implements 2 interface
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao{
	
	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	//find user by id
	public User findById(int id) {
		User user = getByKey(id);
        return user;
	}

	//find user by email
	public User findByEmail(String email) {
		logger.info("email : {}", email);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("email", email));
        User user = (User)crit.uniqueResult();
        return user;
	}
	
	@Override
	public User findBUsername(String username) {
		logger.info("username : {}", username);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("username", username));
        User user = (User)crit.uniqueResult();
        return user;
	}

	//save user
	@Override
	public void save(User user) {
		persist(user);
	}

	//deleting user by email
	@Override
	public void deleteByUsername(String username) {
		Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("username", username));
        User user = (User)crit.uniqueResult();
        delete(user);
	}

	
	// find all users
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<User> users = (List<User>) criteria.list();
        return users;
	}

}
