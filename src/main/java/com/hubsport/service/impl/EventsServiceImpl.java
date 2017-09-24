package com.hubsport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hubsport.dao.EventsDao;
import com.hubsport.domain.Events;
import com.hubsport.service.EventsService;

@Service("eventsService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class EventsServiceImpl implements EventsService {

	@Autowired
	private EventsDao eventsDao;
	
	public Events findById(int id) {
		return eventsDao.findbyid(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveEvents(Events events) {
		eventsDao.save(events);
	}

	@Override
	public void deleteEventsById(Integer id) {
		eventsDao.deleteById(id);		
	}

	@Override
	public List<Events> findAllEvents() {
		return eventsDao.findAllEventsHibernate();
	}
	
	
 
	
}