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
import org.springframework.stereotype.Repository;

import com.hubsport.domain.Timetable;

@Repository("timetableDao")
public class TimetableDaoImpl implements TimetableDao{
	
	static final Logger logger = LoggerFactory.getLogger(TimetableDaoImpl.class);
	
    @Autowired
    private SessionFactory sessionFactory;
	
	//find Places by id
	public Timetable findbyid(Integer id) {
		logger.info("id : {}", id);
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Timetable.class);
		crit.add(Restrictions.eq("id", id));
		Timetable timetable= (Timetable) crit.uniqueResult();
		if (timetable != null) {
			Hibernate.initialize(timetable);
		}
		return timetable;
	}

	//save user
	@Override
	public void save(Timetable timetable) {
		logger.info("imetable : {}", timetable);
		sessionFactory.getCurrentSession().save(timetable);
	}
	
	//update user
	public void update(Timetable timetable) {
		sessionFactory.getCurrentSession().update(timetable);
	}

	//deleting places by id
	@Override
	public void deleteById(Integer id) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Timetable.class);
        crit.add(Restrictions.eq("id", id));
        Timetable timetable = (Timetable)crit.uniqueResult();
        sessionFactory.getCurrentSession().delete(timetable);
	}


	@SuppressWarnings("unchecked")
	public List<Timetable> findAllTimetableHibernate() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Timetable.class);
		crit.addOrder(Order.asc("date"));
		List<Timetable> timetable = crit.list();
		return timetable;
	}
	
	

}