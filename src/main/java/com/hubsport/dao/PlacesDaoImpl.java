 package com.hubsport.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

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
import org.springframework.transaction.annotation.Propagation;

import com.hubsport.domain.Districts;
import com.hubsport.domain.Places;
import com.hubsport.domain.Towns;
import com.hubsport.domain.Users;

@Repository("placeDao")
public class PlacesDaoImpl implements PlacesDao{
	
	static final Logger logger = LoggerFactory.getLogger(PlacesDaoImpl.class);
	
    @Autowired
    private SessionFactory sessionFactory;
	
	//find Places by id
	public Places findbyid(Integer id) {
		logger.info("id : {}", id);
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Places.class);
		crit.add(Restrictions.eq("id", id));
		Places places= (Places) crit.uniqueResult();
		if (places != null) {
			Hibernate.initialize(places);
		}
		return places;
	}

	//save user
	@Override
	public void save(Places places) {
		logger.info("places : {}", places);
		sessionFactory.getCurrentSession().save(places);
	}
	
	
	//update user
	public void update(Places places) {
		sessionFactory.getCurrentSession().update(places);
	}

	//deleting places by id
	@Override
	public void deleteById(Integer id) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Places.class);
		logger.info("id : {}", id);
		crit.add(Restrictions.eq("id", id));
        Places places = (Places)crit.uniqueResult();
        sessionFactory.getCurrentSession().delete(places);
	}

	
	// find all places
	@SuppressWarnings("unchecked")
	@Override
	public List<Places> findAllPlaces() {
		
		String sql = "SELECT p.id as id, p.name as placeName, p.address as placeAddress,  t.name as townName, d.name as district FROM places as p, towns as t, districts as d where p.towns_id=t.id and t.districts_id= d.id";
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		
        List<Places> places = (List<Places>) query.list();
        logger.info("places : {}", places);
		
		
        return places;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Places> findAllPlacesHibernate() {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(Places.class);
			List<Places> places = crit.list();
			return places;
	}
	
	
	// find all Places
	@SuppressWarnings("unchecked")
	@Override
	public List<Places> findPlaces(Integer start, Integer lenght) {
		
		String sql = "SELECT p.id as id, p.name as placeName, p.address as placeAddress,  t.name as townName, d.name as district FROM places as p, towns as t, districts as d where p.towns_id=t.id and t.districts_id= d.id";
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		query.setFirstResult(start);
		query.setMaxResults(lenght);
		
        List<Places> places = (List<Places>) query.list();
        logger.info("places : {}", places);
		
		
        return places;
	}
	
	
	@Override
	public List<Places> findPlaces(String district, Integer start, Integer lenght) {
		
		String sql = "SELECT p.id as id, p.name as placeName, p.address as placeAddress,  t.name as townName, d.name as district FROM places as p, towns as t, districts as d where p.towns_id=t.id and t.districts_id= d.id and d.name like '%" + district + "'%";
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		query.setFirstResult(start);
		query.setMaxResults(lenght);
		
        List<Places> places = (List<Places>) query.list();
        logger.info("places : {}", places);
		
		
        return places;
	}

	public Long countPlaces() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Places.class);
		 criteria.setProjection(Projections.rowCount());
		 Long resultCount = (Long)criteria.uniqueResult();
		return resultCount;
	}
	
}