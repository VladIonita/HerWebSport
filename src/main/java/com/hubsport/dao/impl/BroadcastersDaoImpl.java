package com.hubsport.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hubsport.dao.BroadcastersDao;
import com.hubsport.domain.Broadcasters;

@Repository("broadcastersDao")
public class BroadcastersDaoImpl implements BroadcastersDao {

	static final Logger logger = LoggerFactory.getLogger(BroadcastersDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public Broadcasters findBroadcasterById(Integer id) {
		logger.info("id : {}", id);
		Broadcasters broadcasters = (Broadcasters) createCriteria().add(Restrictions.eq("id", id)).uniqueResult();
		if (broadcasters != null) {
			Hibernate.initialize(broadcasters);
		}
		return broadcasters;
	}

	public void saveBroadcaster(Broadcasters broadcasters) {
		logger.info("broadcasters : {}", broadcasters);
		sessionFactory.getCurrentSession().save(broadcasters);
	}

	public void deleteBroadcasterById(Integer id) {
		logger.info("id : {}", id);
		Broadcasters broadcasters = (Broadcasters) createCriteria().add(Restrictions.eq("id", id)).uniqueResult();
		sessionFactory.getCurrentSession().delete(broadcasters);
	}

	@SuppressWarnings("unchecked")
	public List<Broadcasters> findAllBroadcasters() {
		logger.info("Get all Broadcasters");
		return createCriteria().list();
	}

	private Criteria createCriteria() {
		return sessionFactory.getCurrentSession().createCriteria(Broadcasters.class);
	}

}