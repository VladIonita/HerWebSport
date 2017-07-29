package com.hubsport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hubsport.dao.PlacesDao;
import com.hubsport.dao.UserDao;
import com.hubsport.domain.Districts;
import com.hubsport.domain.Places;
import com.hubsport.domain.Towns;
import com.hubsport.domain.User;

@Service("districtsService")
@Transactional
public class DistrictsServiceImpl implements DistrictsService {

	@Autowired
	private PlacesDao placeDao;

	@Override
	public List<Districts> findAllDistricts() {
		return placeDao.findAllDistricts();
	}

}