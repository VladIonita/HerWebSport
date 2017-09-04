package com.hubsport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hubsport.dao.PlacesDao;
import com.hubsport.dao.TimetableDao;
import com.hubsport.dao.TownsDao;
import com.hubsport.domain.Events;
import com.hubsport.domain.Places;
import com.hubsport.domain.Timetable;
import com.hubsport.domain.Towns;

@Service("timetableService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TimetableServiceImpl implements TimetableService {

	@Autowired
	private TimetableDao timetableDao;
	
	public Timetable findById(Integer id) {
		return timetableDao.findbyid(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveTimetable(Timetable timetable) {
		timetableDao.save(timetable);
	}

	@Override
	public void deleteTimetableById(Integer id) {
		timetableDao.deleteById(id);		
	}

	@Override
	public List<Timetable> findAllTimetable() {
		return timetableDao.findAllTimetableHibernate();
	}
 
	@Override
	public List<Events> findTimetable(Integer start, Integer lenght) {
		return timetableDao.findTimetable(start, lenght);
	}
	
	@Override
	public Long countGet() {
		return timetableDao.countTimetable();
	}

	@Override
	public List<Timetable> findTimetable(Integer id, Integer start, Integer lenght) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}