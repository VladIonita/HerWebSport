package com.hubsport.service;

import java.util.List;

import com.hubsport.domain.Places;

public interface PlacesService {

	Places findPlaceById(Integer id);

	void saveOrUpdatePlace(Places place);

	void deletePlaceById(Integer id);

	List<Places> findPlacesWithStartAndLength(Integer start, Integer length);

	List<Places> findPlacesWithIdStartAndLength(Integer id, Integer start, Integer length);

	Long countGet();

}