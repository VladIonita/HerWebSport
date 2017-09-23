package com.hubsport.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hubsport.domain.Categories;

@Repository("categoriesDao")
public class CategoriesDaoImpl implements CategoriesDao {

	static final Logger logger = LoggerFactory.getLogger(CategoriesDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public Categories findCategoriesById(Integer id) {
		logger.info("id : {}", id);
		Categories categories = (Categories) createCriteria().add(Restrictions.eq("id", id)).uniqueResult();
		if (categories != null) {
			Hibernate.initialize(categories);
		}
		return categories;
	}

	public void saveCategories(Categories categories) {
		logger.info("categories : {}", categories);
		sessionFactory.getCurrentSession().save(categories);
	}

	public void deleteCategoriesById(Integer id) {
		logger.info("id : {}", id);
		Categories categories = (Categories) createCriteria().add(Restrictions.eq("id", id)).uniqueResult();
		sessionFactory.getCurrentSession().delete(categories);
	}

	@SuppressWarnings("unchecked")
	public List<Categories> findAllCategories() {
		logger.info("Get all Categories");
		return createCriteria().list();
	}

	private Criteria createCriteria() {
		return sessionFactory.getCurrentSession().createCriteria(Categories.class);
	}

}