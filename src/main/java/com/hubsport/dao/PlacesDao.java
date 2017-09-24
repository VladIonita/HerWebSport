package com.hubsport.dao;

import java.util.List;

import com.hubsport.domain.Places;

public interface PlacesDao {

	Places findPlaceById(Integer id);

	void savePlace(Places place);

	void deletePlaceById(Integer id);

	List<Places> findPlaces(Integer start, Integer lenght);

	List<Places> findPlaces(Integer id, Integer start, Integer lenght);

	Long countPlaces();

}
