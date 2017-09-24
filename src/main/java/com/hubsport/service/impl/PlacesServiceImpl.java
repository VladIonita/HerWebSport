package com.hubsport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hubsport.dao.PlacesDao;
import com.hubsport.domain.Places;
import com.hubsport.service.PlacesService;

@Service("placesService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PlacesServiceImpl implements PlacesService {

	@Autowired
	private PlacesDao placeDao;

	public Places findPlaceById(Integer id) {
		return placeDao.findPlaceById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveOrUpdatePlace(Places place) {
		if (findPlaceById(place.getId()) == null) {
			placeDao.savePlace(place);
		} else {
			Places entity = placeDao.findPlaceById(place.getId());
			if (entity != null) {
				entity.setId(place.getId());
				entity.setAddress(place.getAddress());
				entity.setNamePlaces(place.getNamePlaces());
				entity.setTowns(place.getTowns());
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deletePlaceById(Integer id) {
		placeDao.deletePlaceById(id);
	}

	public List<Places> findPlacesWithStartAndLength(Integer start, Integer lenght) {
		return placeDao.findPlaces(start, lenght);
	}

	public List<Places> findPlacesWithIdStartAndLength(Integer id, Integer start, Integer lenght) {
		return placeDao.findPlaces(id, start, lenght);
	}

	public Long countGet() {
		return placeDao.countPlaces();
	}

}