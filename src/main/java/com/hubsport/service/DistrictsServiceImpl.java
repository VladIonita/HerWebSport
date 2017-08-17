package com.hubsport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hubsport.dao.DistrictsDao;
import com.hubsport.dao.PlacesDao;
import com.hubsport.dao.UserDao;
import com.hubsport.domain.Districts;
import com.hubsport.domain.Places;
import com.hubsport.domain.Towns;
import com.hubsport.domain.Users;

@Service("districtsService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DistrictsServiceImpl implements DistrictsService {

	@Autowired
	private DistrictsDao districtsDao;

	@Override
	public List<Districts> findAllDistricts() {
		return districtsDao.findAllDistrictsHibernate();
	}

	@Override
	public Districts findById(Integer id) {
		return districtsDao.findbyid(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveDistricts(Districts districts) {
		districtsDao.save(districts);		
	}

	@Override
	public void deleteDistrictsById(Integer id) {
		districtsDao.deleteById(id);		
	}

	
}