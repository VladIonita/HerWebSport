package com.hubsport.dao;

import java.util.List;

import com.hubsport.domain.Districts;

public interface DistrictsDao {

	Districts findbyid(Integer id);
	
	void save(Districts districts);

	void deleteById(Integer id);

	List<Districts> findAllDistrictsHibernate();
	
}
