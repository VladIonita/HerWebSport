package com.hubsport.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hubsport.dao.DistrictsDao;
import com.hubsport.domain.Districts;

@Repository("districtsDao")
public class DistrictsDaoImpl implements DistrictsDao {

	static final Logger logger = LoggerFactory.getLogger(DistrictsDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Districts> findAllDistricts() {
		return createCriteria().list();
	}

	private Criteria createCriteria() {
		return sessionFactory.getCurrentSession().createCriteria(Districts.class);
	}
}