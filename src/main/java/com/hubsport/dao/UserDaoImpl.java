package com.hubsport.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hubsport.domain.User;

//implements 2 interface
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao{
	
	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	
	//find user by id
	@Override
	public User findById(int id) {
		User user = findById(id);
		if(user!= null){
			Hibernate.initialize(user.getStatus());
		}
		return user;
	}

	//find user by email
	@Override
	public User findByEmail(String email) {
		logger.info("Email : {}", email);
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("email", email));
		User user = (User)criteria.uniqueResult();
		if(user!=null) {
			Hibernate.initialize(user.getStatus());
		}
		return user;
	}

	//save user
	@Override
	public void save(User user) {
		persist(user);
	}

	//deleting user by email
	@Override
	public void deleteByEmail(String email) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("email", email));
		User user = (User)criteria.uniqueResult();
		delete(user);
	}

	
	// find all users
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); //avoiding duplicate
		List<User> users =(List<User>) criteria.list();
		return users;
	}

}
