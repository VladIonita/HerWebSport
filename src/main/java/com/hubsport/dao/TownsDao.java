package com.hubsport.dao;

import java.util.List;

import com.hubsport.domain.Towns;

public interface TownsDao {

	Towns findbyid(Integer id);
	
	void save(Towns towns);

	void deleteById(Integer id);

	List<Towns> findAllTowns();
	
	List<Towns> findAllTownsHibernate();
	
}
