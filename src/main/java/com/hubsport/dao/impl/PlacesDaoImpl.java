package com.hubsport.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hubsport.dao.PlacesDao;
import com.hubsport.domain.Places;

@Repository("placeDao")
public class PlacesDaoImpl implements PlacesDao {

	static final Logger logger = LoggerFactory.getLogger(PlacesDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public Places findPlaceById(Integer id) {
		logger.info("id : {}", id);
		Places place = (Places) createCriteria().add(Restrictions.eq("id", id)).uniqueResult();
		if (place != null) {
			Hibernate.initialize(place);
		}
		return place;
	}

	public void savePlace(Places place) {
		logger.info("places : {}", place);
		sessionFactory.getCurrentSession().save(place);
	}

	public void update(Places place) {
		sessionFactory.getCurrentSession().update(place);
	}

	public void deletePlaceById(Integer id) {
		logger.info("id : {}", id);
		sessionFactory.getCurrentSession().delete(createCriteria().add(Restrictions.eq("id", id)).uniqueResult());
	}

	@SuppressWarnings("unchecked")
	public List<Places> findPlaces(Integer start, Integer length) {
		logger.info("Get all Places from start : {}", start, " with length : {}", length);
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
				"SELECT p.id as id, p.name as placeName, p.address as placeAddress,  t.name as townName,"
						+ " d.name as district FROM places as p, towns as t, districts as d where p.towns_id=t.id and"
						+ " t.districts_id= d.id");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return (List<Places>) query.list();

	}

	@SuppressWarnings("unchecked")
	public List<Places> findPlaces(Integer id, Integer start, Integer length) {
		logger.info("Get all Places with id : {}", id, " from start : {}", start, " with length : {}", length);
		SQLQuery query = sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT p.id as id, p.name as placeName,"
						+ " p.address as placeAddress,  t.name as townName, d.name as district FROM places as p, towns as t, "
						+ "districts as d where p.towns_id=t.id and t.districts_id= d.id and d.id = ? ");
		query.setInteger(0, id);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return (List<Places>) query.list();
	}

	public Long countPlaces() {
		logger.info("Count Places");
		return (Long) createCriteria().setProjection(Projections.rowCount()).uniqueResult();
	}

	private Criteria createCriteria() {
		return sessionFactory.getCurrentSession().createCriteria(Places.class);
	}

}