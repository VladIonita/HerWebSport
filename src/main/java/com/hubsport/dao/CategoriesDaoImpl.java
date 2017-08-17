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

import com.hubsport.domain.Categories;
import com.hubsport.domain.Districts;
import com.hubsport.domain.Towns;

@Repository("categoriesDao")
public class CategoriesDaoImpl implements CategoriesDao{
	
	static final Logger logger = LoggerFactory.getLogger(CategoriesDaoImpl.class);
	
    @Autowired
    private SessionFactory sessionFactory;
	
	//find Places by id
	public Categories findbyid(Integer id) {
		logger.info("id : {}", id);
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Categories.class);
		crit.add(Restrictions.eq("id", id));
		Categories categories= (Categories) crit.uniqueResult();
		if (categories != null) {
			Hibernate.initialize(categories);
		}
		return categories;
	}

	//save user
	@Override
	public void save(Categories categories) {
		logger.info("categories : {}", categories);
		sessionFactory.getCurrentSession().save(categories);
	}
	
	//deleting places by id
	@Override
	public void deleteById(Integer id) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Categories.class);
        crit.add(Restrictions.eq("id", id));
        Categories categories = (Categories)crit.uniqueResult();
        sessionFactory.getCurrentSession().delete(categories);
	}


	@SuppressWarnings("unchecked")
	public List<Categories> findAllCategoriesHibernate() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Categories.class);
		List<Categories> categories = crit.list();
		return categories;
	}
	
	

}