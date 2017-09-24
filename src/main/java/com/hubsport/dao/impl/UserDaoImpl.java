package com.hubsport.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hubsport.dao.UserDao;
import com.hubsport.domain.Users;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public Users findUserById(Integer id) {
		logger.info("id : {}", id);
		Users users = (Users) createCritera().add(Restrictions.eq("id", id)).uniqueResult();
		if (users != null) {
			Hibernate.initialize(users);
		}
		return users;
	}

	public Users findUserByEmail(String email) {
		logger.info("email : {}", email);
		Users users = (Users) createCritera().add(Restrictions.eq("email", email)).uniqueResult();
		if (users != null) {
			Hibernate.initialize(users);
		}
		return users;
	}

	@SuppressWarnings("unchecked")
	public List<Users> findUsersForJson(Integer start, Integer lenght) {
		logger.info("Get all Users for Json");
		Criteria criteria = createCritera()
				.setProjection(Projections.projectionList().add(Projections.property("id"), "id")
						.add(Projections.property("email"), "email").add(Projections.property("firstName"), "firstName")
						.add(Projections.property("lastName"), "lastName"))
				.setResultTransformer(Transformers.aliasToBean(Users.class)).addOrder(Order.asc("firstName"))
				.setMaxResults(lenght).setFirstResult(start);
		return (List<Users>) criteria.list();
	}

	public Long countUsers() {
		logger.info("Count Users");
		return (Long) createCritera().setProjection(Projections.rowCount()).uniqueResult();
	}

	public void saveUser(Users user) {
		logger.info("user : {}", user);
		sessionFactory.getCurrentSession().persist(user);
	}

	public void updateUser(Users user) {
		logger.info("user : {}", user);
		sessionFactory.getCurrentSession().update(user);
	}

	public void deleteUserById(Integer id) {
		logger.info("id : {}", id);
		Criteria crit = createCritera().add(Restrictions.eq("id", id));
		sessionFactory.getCurrentSession().delete(crit.uniqueResult());
	}

	private Criteria createCritera() {
		return sessionFactory.getCurrentSession().createCriteria(Users.class);
	}
}