package com.hubsport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hubsport.dao.PlacesDao;
import com.hubsport.dao.UserDao;
import com.hubsport.domain.Places;
import com.hubsport.domain.Towns;
import com.hubsport.domain.User;

@Service("placesService")
@Transactional
public class PlacesServiceImpl implements PlacesService {

	@Autowired
	private PlacesDao placeDao;
	
	public Places findById(int id) {
		return placeDao.findbyid(id);
	}

	public void savePlace(Places places) {
		placeDao.save(places);
	}

	public void updatePlace(Places places) {
		Places entity = placeDao.findbyid(places.getId());
		if(entity!=null) {
			entity.setAddress(places.getAddress());
			entity.setName(places.getName());
			entity.setTowns(places.getTowns());
		}
	}

	public void deletePlacesById(Integer id) {
		placeDao.deleteById(id);
	}

	public List<Places> findAllPlaces() {
		return placeDao.findAllPlaces();
	}

	@Override
	public List<Towns> findAllTowns() {
		return placeDao.findAllTowns();
	}

}