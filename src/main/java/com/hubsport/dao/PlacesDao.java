package com.hubsport.dao;

import java.util.List;

import com.hubsport.domain.Districts;
import com.hubsport.domain.Places;
import com.hubsport.domain.Towns;
import com.hubsport.domain.Users;

public interface PlacesDao {

	Places findbyid(Integer id);
	
	void save(Places places);

	void deleteById(Integer id);

	List<Places> findAllPlaces();
	
	List<Places> findAllPlacesHibernate();
	
	Long countPlaces();

	List<Places> findPlaces(Integer start, Integer lenght);
	
	List<Places> findPlaces(String district, Integer start, Integer lenght);
}
