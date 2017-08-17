package com.hubsport.dao;

import java.util.List;

import com.hubsport.domain.Broadcasters;
import com.hubsport.domain.Categories;
import com.hubsport.domain.Districts;

public interface BroadcastersDao {

	Broadcasters findbyid(Integer id);
	
	void save(Broadcasters broadcasters);

	void deleteById(Integer id);

	List<Broadcasters> findAllBroadcastersHibernate();
	
}
