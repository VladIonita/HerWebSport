package com.hubsport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hubsport.dao.PlacesDao;
import com.hubsport.domain.Towns;

@Service("townsService")
@Transactional
public class TownsServiceImpl implements TownsService {

	@Autowired
	private PlacesDao placeDao;
	
	@Override
	public List<Towns> findAllTowns() {
		return placeDao.findAllTowns();
	}

}