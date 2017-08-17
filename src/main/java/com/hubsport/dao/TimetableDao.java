package com.hubsport.dao;

import java.util.List;

import com.hubsport.domain.Districts;
import com.hubsport.domain.Timetable;

public interface TimetableDao {

	Timetable findbyid(Integer id);
	
	void save(Timetable timetable);

	void deleteById(Integer id);

	List<Timetable> findAllTimetableHibernate();
	
}
