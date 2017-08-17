 package com.hubsport.dao;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hubsport.domain.Broadcasters;

@Repository("broadcastersDao")
public class BroadcastersDaoImpl implements BroadcastersDao{
	
	static final Logger logger = LoggerFactory.getLogger(BroadcastersDaoImpl.class);
	
    @Autowired
    private SessionFactory sessionFactory;
	
	//find Places by id
	public Broadcasters findbyid(Integer id) {
		logger.info("id : {}", id);
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Broadcasters.class);
		crit.add(Restrictions.eq("id", id));
		Broadcasters broadcasters= (Broadcasters) crit.uniqueResult();
		if (broadcasters != null) {
			Hibernate.initialize(broadcasters);
		}
		return broadcasters;
	}

	//save user
	@Override
	public void save(Broadcasters broadcasters) {
		logger.info("broadcasters : {}", broadcasters);
		sessionFactory.getCurrentSession().save(broadcasters);
	}
	
	//deleting places by id
	@Override
	public void deleteById(Integer id) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Broadcasters.class);
        crit.add(Restrictions.eq("id", id));
        Broadcasters broadcasters = (Broadcasters)crit.uniqueResult();
        sessionFactory.getCurrentSession().delete(broadcasters);
	}


	@SuppressWarnings("unchecked")
	public List<Broadcasters> findAllBroadcastersHibernate() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Broadcasters.class);
		List<Broadcasters> broadcasters = crit.list();
		return broadcasters;
	}
	
	

}