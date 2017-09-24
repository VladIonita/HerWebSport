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

import com.hubsport.dao.TownsDao;
import com.hubsport.domain.Towns;

@Repository("townsDao")
public class TownsDaoImpl implements TownsDao {

	static final Logger logger = LoggerFactory.getLogger(TownsDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public Towns findTownById(Integer id) {
		logger.info("id : {}", id);
		Towns town = (Towns) createCriteria().add(Restrictions.eq("id", id)).uniqueResult();
		if (town != null) {
			Hibernate.initialize(town);
		}
		return town;
	}

	public Towns findTownByName(String name) {
		logger.info("nameTowns : {}", name);
		Towns town = (Towns) createCriteria().add(Restrictions.eq("nameTowns", name)).uniqueResult();
		if (town != null) {
			Hibernate.initialize(town);
		}
		return town;
	}

	public void saveTown(Towns town) {
		logger.info("towns : {}", town);
		sessionFactory.getCurrentSession().save(town);
	}

	public void update(Towns town) {
		logger.info("towns : {}", town);
		sessionFactory.getCurrentSession().update(town);
	}

	public void deleteTownById(Integer id) {
		logger.info("id : {}", id);
		sessionFactory.getCurrentSession().delete(createCriteria().add(Restrictions.eq("id", id)).uniqueResult());
	}

	@SuppressWarnings("unchecked")
	public List<Towns> findAllTowns() {
		logger.info("Get all Towns");
		return createCriteria().list();
	}

	@SuppressWarnings("unchecked")
	public List<Towns> findAllTowns(String name) {
		logger.info("Get all Towns for Json that starts with name : {}", name);
		SQLQuery querylist = sessionFactory.getCurrentSession().createSQLQuery(
				"SELECT t.id as id, t.name as townName, d.name as district FROM towns as t, districts as d where "
						+ "t.districts_id=d.id and t.name LIKE :name");
		querylist.setParameter("name", name + "%");
		querylist.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return (List<Towns>) querylist.list();
	}

	private Criteria createCriteria() {
		return sessionFactory.getCurrentSession().createCriteria(Towns.class);
	}

}