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

import com.hubsport.domain.Districts;
import com.hubsport.domain.Towns;

@Repository("districtsDao")
public class DistrictsDaoImpl implements DistrictsDao{
	
	static final Logger logger = LoggerFactory.getLogger(DistrictsDaoImpl.class);
	
    @Autowired
    private SessionFactory sessionFactory;
	
	//find Places by id
	public Districts findbyid(Integer id) {
		logger.info("id : {}", id);
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Districts.class);
		crit.add(Restrictions.eq("id", id));
		Districts districts= (Districts) crit.uniqueResult();
		if (districts != null) {
			Hibernate.initialize(districts);
		}
		return districts;
	}

	//save user
	@Override
	public void save(Districts districts) {
		logger.info("towns : {}", districts);
		sessionFactory.getCurrentSession().save(districts);
	}
	
	//deleting places by id
	@Override
	public void deleteById(Integer id) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Districts.class);
        crit.add(Restrictions.eq("id", id));
        Districts districts = (Districts)crit.uniqueResult();
        sessionFactory.getCurrentSession().delete(districts);
	}


	@SuppressWarnings("unchecked")
	public List<Districts> findAllDistrictsHibernate() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Districts.class);
		List<Districts> districts = crit.list();
		return districts;
	}
	
	

}