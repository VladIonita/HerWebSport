 package com.hubsport.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hubsport.domain.Districts;
import com.hubsport.domain.Places;
import com.hubsport.domain.Towns;

@Repository("placeDao")
public class PlacesDaoImpl implements PlacesDao{
	
	static final Logger logger = LoggerFactory.getLogger(PlacesDaoImpl.class);
	
    @Autowired
    private SessionFactory sessionFactory;
	
	//find Places by id
	public Places findbyid(Integer id) {
		
		
		Places places = (Places)sessionFactory.getCurrentSession().get(Places.class, id);
		if(places!=null){
			Hibernate.initialize(places);
		}
        return places;
	}

	//save user
	@Override
	public void save(Places places) {
		logger.info("places : {}", places);
		sessionFactory.getCurrentSession().persist(places);
	}

	//deleting places by id
	@Override
	public void deleteById(Integer id) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Places.class);
        crit.add(Restrictions.eq("id", id));
        Places places = (Places)crit.uniqueResult();
        sessionFactory.getCurrentSession().delete(places);
	}

	
	// find all places
	@SuppressWarnings("unchecked")
	@Override
	public List<Places> findAllPlaces() {
		
		String sql = "SELECT p.name as placeName, p.address as placeAddress,  t.name as townName, d.name as district FROM places as p, towns as t, districts as d where p.towns_id=t.id and t.districts_id= d.id";
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		
        List<Places> places = (List<Places>) query.list();
        logger.info("places : {}", places);
		
		
        return places;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Towns> findAllTowns() {
		String sql = "SELECT t.name as townName, d.name as district FROM towns as t, districts as d where t.districts_id=d.id";
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		
        List<Towns> towns = (List<Towns>) query.list();
        logger.info("towns : {}", towns);
        return towns;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Districts> findAllDistricts() {
		String sql = "SELECT * from districts";
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		
        List<Districts> districts = (List<Districts>) query.list();
        logger.info("districts : {}", districts);
        return districts;
	}
	
}