package com.hubsport.dao;

import java.util.List;

import com.hubsport.domain.Categories;

public interface CategoriesDao {

	Categories findCategoriesById(Integer id);

	void saveCategories(Categories categories);

	void deleteCategoriesById(Integer id);

	List<Categories> findAllCategories();

}
