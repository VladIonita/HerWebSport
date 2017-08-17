 package com.hubsport.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;

import com.hubsport.domain.Towns;

@Repository("townsDao")
public class TownsDaoImpl implements TownsDao{
	
	static final Logger logger = LoggerFactory.getLogger(TownsDaoImpl.class);
	
    @Autowired
    private SessionFactory sessionFactory;
	
	//find Places by id
	public Towns findbyid(Integer id) {
		logger.info("id : {}", id);
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Towns.class);
		crit.add(Restrictions.eq("id", id));
		Towns towns= (Towns) crit.uniqueResult();
		if (towns != null) {
			Hibernate.initialize(towns);
		}
		return towns;
	}

	//save user
	@Override
	public void save(Towns towns) {
		logger.info("towns : {}", towns);
		sessionFactory.getCurrentSession().save(towns);
	}
	
	//update user
	public void update(Towns towns) {
		sessionFactory.getCurrentSession().update(towns);
	}

	//deleting places by id
	@Override
	public void deleteById(Integer id) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Towns.class);
        crit.add(Restrictions.eq("id", id));
        Towns towns = (Towns)crit.uniqueResult();
        sessionFactory.getCurrentSession().delete(towns);
	}

	// find all places
	@SuppressWarnings("unchecked")
	public List<Towns> findAllTowns() {
		String sql = "SELECT t.name as townName, d.name as district FROM towns as t, districts as d where t.districts_id=d.id";
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Towns> towns = (List<Towns>) query.list();
        logger.info("towns : {}", towns);
        return towns;
	}

	@SuppressWarnings("unchecked")
	public List<Towns> findAllTownsHibernate() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Towns.class);
		List<Towns> towns = crit.list();
		return towns;
	}
	
	

}