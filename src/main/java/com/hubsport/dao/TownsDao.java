package com.hubsport.dao;

import java.util.List;

import com.hubsport.domain.Towns;

public interface TownsDao {

	Towns findTownById(Integer id);
	
	Towns findTownByName(String name);
	
	void saveTown(Towns towns);

	void deleteTownById(Integer id);

	List<Towns> findAllTowns();
	
	List<Towns> findAllTowns(String query);
	
}
