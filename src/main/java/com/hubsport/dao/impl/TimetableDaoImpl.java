 package com.hubsport.dao.impl;

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
import org.springframework.stereotype.Repository;

import com.hubsport.dao.TimetableDao;
import com.hubsport.domain.Events;
import com.hubsport.domain.Places;
import com.hubsport.domain.Timetable;
import com.hubsport.domain.Users;

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
//		crit.createAlias("events", "e");
		
//		crit.addOrder(Order.asc("date"));
		List<Timetable> timetable = (List<Timetable>) crit.list();
		return timetable;
	}
	
	// find all Places
	@SuppressWarnings("unchecked")
	@Override
	public List<Timetable> findTimetable(Integer start, Integer lenght) {
		
		String sql = "SELECT DATE(time.time) as time,TIME(time.time) as hour, ev.name as eventsName, p.name as placeName, " +
		"p.address as placeAddress, t.name as townName, d.name as districtName, cat.name as categoriesName, " +
		"b.name as broadName FROM timetable as time, events as ev, places as p, towns as t, districts as d, " +
		"categories as cat, broadcasters as b where time.events_id=ev.id and ev.places_id=p.id and " +
		"ev.categories_id=cat.id and ev.broadcasters_id=b.id and p.towns_id=t.id and t.districts_id= d.id ORDER BY time.time";
		
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		query.setFirstResult(start);
		query.setMaxResults(lenght);
		
        List<Timetable> timetables = (List<Timetable>) query.list();
        logger.info("places : {}", timetables);
		
        return timetables;
		
//		// find all users
//			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Events.class, "ev");
//								criteria.createCriteria("timetable", "t")
//					
////							  .createAlias("time.Events", "ev")
////							  .setProjection(Projections.projectionList()
////									  .add(Projections.property("id"), "id")
////									  .add(Projections.property("date"), "date"))
////									  .add(Projections.property("events.id"), "events_x	id"))
////									  .setResultTransformer(Transformers.aliasToBean(Timetable.class))
////									  .addOrder(Order.asc("date"))
//									  .setMaxResults(lenght)
//									  .setFirstResult(start);
//			
//			List<Events> events = (List<Events>) criteria.list();
//			System.out.println("Lungimea este " + events.size());
//			return events;
	}
	
	public Long countTimetable() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Timetable.class);
		 criteria.setProjection(Projections.rowCount());
		 Long resultCount = (Long)criteria.uniqueResult();
		return resultCount;
	}

	@Override
	public List<Timetable> findTimetable(Integer id, Integer start, Integer lenght) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}