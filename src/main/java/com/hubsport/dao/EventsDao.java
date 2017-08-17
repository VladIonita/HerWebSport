package com.hubsport.dao;

import java.util.List;

import com.hubsport.domain.Districts;
import com.hubsport.domain.Events;
import com.hubsport.domain.Timetable;

public interface EventsDao {

	Events findbyid(Integer id);
	
	void save(Events events);

	void deleteById(Integer id);

	List<Events> findAllEventsHibernate();
	
	List<Events> findAllEvents();
}
