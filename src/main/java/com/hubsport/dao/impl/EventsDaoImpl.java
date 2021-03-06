package com.hubsport.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hubsport.dao.EventsDao;
import com.hubsport.domain.Events;
import com.hubsport.domain.Timetable;

@Repository("eventsDao")
public class EventsDaoImpl implements EventsDao {

	static final Logger logger = LoggerFactory.getLogger(EventsDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	// find Places by id
	public Events findbyid(Integer id) {
		logger.info("id : {}", id);
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Events.class);
		crit.add(Restrictions.eq("id", id));
		Events events = (Events) crit.uniqueResult();
		if (events != null) {
			Hibernate.initialize(events);
		}
		return events;
	}

	// save user
	@Override
	public void save(Events events) {
		logger.info("events : {}", events);
		sessionFactory.getCurrentSession().save(events);
	}

	// deleting places by id
	@Override
	public void deleteById(Integer id) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Events.class);
		crit.add(Restrictions.eq("id", id));
		Events events = (Events) crit.uniqueResult();
		sessionFactory.getCurrentSession().delete(events);
	}

	@SuppressWarnings("unchecked")
	public List<Events> findAllEventsHibernate() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Events.class);
		List<Events> events = crit.list();
		return events;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List findAllEvents() {

		// String sql = "SELECT * FROM hubsport.events";
		// SQLQuery query =
		// sessionFactory.getCurrentSession().createSQLQuery(sql);
		// query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		//
		// List<Events> events = (List<Events>) query.list();
		// logger.info("events : {}", events);
		//
		//
		// return events;

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Timetable.class);
		Criteria critTimetable = criteria.createCriteria("events");
//		  .setProjection(Projections.projectionList()
//				  .add(Projections.property("id"), "id")
//				  .add(Projections.property("date"), "date"))
//				  .add(Projections.property("events.id"), "events_x	id"))
//				  .setResultTransformer(Transformers.aliasToBean(Timetable.class))
//				  .addOrder(Order.asc("date"))

		List events = criteria.list();
		System.out.println("Lungimea este " + events.size());
		return events;
	}

}