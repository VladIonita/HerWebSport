package com.hubsport.service;

import java.util.List;

import com.hubsport.domain.Events;
import com.hubsport.domain.Timetable;

public interface TimetableService {
	
    Timetable findById(Integer id);
    
    void saveTimetable(Timetable timetable);
     
    void deleteTimetableById(Integer id);
 
    List<Timetable> findAllTimetable();
    
    List<Events> findTimetable(Integer start, Integer lenght); 
    
    List<Timetable> findTimetable(Integer id, Integer start, Integer lenght);
    
    Long countGet();
}