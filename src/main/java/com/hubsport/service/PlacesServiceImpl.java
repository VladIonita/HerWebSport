package com.hubsport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hubsport.dao.PlacesDao;
import com.hubsport.domain.Places;

@Service("placesService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PlacesServiceImpl implements PlacesService {

	@Autowired
	private PlacesDao placeDao;
	
	public Places findById(Integer id) {
		return placeDao.findbyid(id);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveOrUpdate(Places places) {
		if (findById(places.getid()) == null) {
			placeDao.save(places);
			
		} else {
			Places entity = placeDao.findbyid(places.getid());
			if (entity != null) {
				entity.setid(places.getid());
				entity.setAddress(places.getAddress());
				entity.setNamePlaces(places.getNamePlaces());
				entity.setTowns(places.getTowns());
			}
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deletePlacesById(Integer id) {
		placeDao.deleteById(id);
	}

	public List<Places> findAllPlaces() {
		return placeDao.findAllPlacesHibernate();
	}
	
	@Override
	public List<Places> findPlaces(Integer start, Integer lenght) {
		return placeDao.findPlaces(start, lenght);
	}
	
	@Override
	public Long countGet() {
		return placeDao.countPlaces();
	}

	@Override
	public List<Places> findPlaces(Integer id, Integer start, Integer lenght) {
		return placeDao.findPlaces(id, start, lenght);
	}
	
	

}