package com.hubsport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hubsport.dao.DistrictsDao;
import com.hubsport.domain.Districts;
import com.hubsport.service.DistrictsService;

@Service("districtsService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DistrictsServiceImpl implements DistrictsService {

	@Autowired
	private DistrictsDao districtsDao;

	@Override
	public List<Districts> findAllDistricts() {
		return districtsDao.findAllDistricts();
	}

}