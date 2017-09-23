package com.hubsport.dao;

import java.util.List;

import com.hubsport.domain.Broadcasters;

public interface BroadcastersDao {

	Broadcasters findBroadcasterById(Integer id);
	
	void saveBroadcaster(Broadcasters broadcasters);

	void deleteBroadcasterById(Integer id);

	List<Broadcasters> findAllBroadcasters();
	
}
