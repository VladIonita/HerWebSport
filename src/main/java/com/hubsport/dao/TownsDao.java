package com.hubsport.dao;

import java.util.List;

import com.hubsport.domain.Towns;

public interface TownsDao {

	Towns findbyid(Integer id);
	
	Towns findbyName(String name);
	
	void save(Towns towns);

	void deleteById(Integer id);

	List<Towns> findAllTowns();
	
	List<Towns> findAllTownsHibernate();
	
	List<Towns> findAllTowns(String query);
	
}
