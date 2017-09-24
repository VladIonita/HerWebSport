package com.hubsport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hubsport.dao.TownsDao;
import com.hubsport.domain.Towns;
import com.hubsport.service.TownsService;

@Service("townsService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TownsServiceImpl implements TownsService {

	@Autowired
	private TownsDao townsDao;

	public Towns findTownById(Integer id) {
		return townsDao.findTownById(id);
	}

	public Towns findTownByName(String name) {
		return townsDao.findTownByName(name);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveTown(Towns towns) {
		townsDao.saveTown(towns);
	}

	public void deleteTownById(Integer id) {
		townsDao.deleteTownById(id);
	}

	public List<Towns> findAllTowns() {
		return townsDao.findAllTowns();
	}

	public List<Towns> findAllTowns(String name) {
		return townsDao.findAllTowns(name);
	}

}