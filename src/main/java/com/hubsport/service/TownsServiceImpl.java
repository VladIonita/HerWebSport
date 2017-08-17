package com.hubsport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hubsport.dao.PlacesDao;
import com.hubsport.dao.TownsDao;
import com.hubsport.domain.Towns;

@Service("townsService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TownsServiceImpl implements TownsService {

	@Autowired
	private TownsDao townsDao;
	
	public Towns findById(int id) {
		return townsDao.findbyid(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveTowns(Towns towns) {
		townsDao.save(towns);
	}

	@Override
	public void deleteTownsById(Integer id) {
		townsDao.deleteById(id);		
	}

	@Override
	public List<Towns> findAllTowns() {
		return townsDao.findAllTownsHibernate();
	}
 
	
}