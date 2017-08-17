package com.hubsport.service;

import java.util.List;

import com.hubsport.domain.Events;

public interface EventsService {
	
	Events findById(int id);
    
    void saveEvents(Events events);
     
    void deleteEventsById(Integer id);
	
	List<Events> findAllEvents(); 

}