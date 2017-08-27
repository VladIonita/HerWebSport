package com.hubsport.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.hubsport.domain.Users;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	// find user by id
	public Users findbyid(Integer id) {
		logger.info("id : {}", id);
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Users.class);
		crit.add(Restrictions.eq("id", id));
		Users users = (Users) crit.uniqueResult();
		if (users != null) {
			Hibernate.initialize(users.getEmail());
		}
		return users;
	}

	// find user by email
	public Users findbyemail(String email) {
		logger.info("email : {}", email);
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Users.class);
		crit.add(Restrictions.eq("email", email));
		Users users = (Users) crit.uniqueResult();
		if (users != null) {
			Hibernate.initialize(users.getEmail());
		}
		return users;
	}

	// find all users
	@SuppressWarnings("unchecked")
	@Override
	public List<Users> findUsers(Integer start, Integer lenght) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Users.class)
						  .setProjection(Projections.projectionList()
								  .add(Projections.property("id"), "id")
								  .add(Projections.property("email"), "email")
								  .add(Projections.property("firstName"), "firstName")
								  .add(Projections.property("lastName"), "lastName"))
								  .setResultTransformer(Transformers.aliasToBean(Users.class))
								  .addOrder(Order.asc("firstName"))
								  .setMaxResults(lenght)
								  .setFirstResult(start);
		
		
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid
		List<Users> users = (List<Users>) criteria.list();
		return users;
	}
 
	public Long countUsers() {
//		Long count = (Long) sessionFactory.getCurrentSession().createQuery("select count(*) from  hubsport.users")
//                .uniqueResult();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Users.class);
		 criteria.setProjection(Projections.rowCount());
		 Long resultCount = (Long)criteria.uniqueResult();
		return resultCount;
	}
	
	// save user
	
	@Override
	public void save(Users users) {
		logger.info("user : {}", users);
		sessionFactory.getCurrentSession().persist(users);
	}
	
	
	//update user
	public void update(Users users) {
		sessionFactory.getCurrentSession().update(users);
	}

	@Override
	public void deleteId(Integer id) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Users.class);
		crit.add(Restrictions.eq("id", id));
		Users users = (Users) crit.uniqueResult();
		sessionFactory.getCurrentSession().delete(users);
	}
}