package com.hubsport.dao;

import java.util.List;

import com.hubsport.domain.Categories;
import com.hubsport.domain.Districts;

public interface CategoriesDao {

	Categories findbyid(Integer id);
	
	void save(Categories categories);

	void deleteById(Integer id);

	List<Categories> findAllCategoriesHibernate();
	
}
