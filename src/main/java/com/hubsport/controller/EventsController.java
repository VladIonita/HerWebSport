package com.hubsport.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hubsport.domain.Events;
import com.hubsport.domain.Places;
import com.hubsport.domain.Timetable;
import com.hubsport.service.CurrentTimeFormated;
import com.hubsport.service.EventsService;
import com.hubsport.service.TimetableService;

@Controller
@RequestMapping(value = "/admin/events")
public class EventsController {
	
	@Autowired
	TimetableService timetableService;
	
	@Autowired
	EventsService eventsService;
	
	@Autowired
	CurrentTimeFormated timeService;
	
	@Autowired
	MessageSource messageSource;

	@RequestMapping
	public String eventsPage(ModelMap model) {
//		model.addAttribute("timetableList", timetableService.findAllTimetable());
		CurrentTimeFormated cur = new CurrentTimeFormated();
		
		model.addAttribute("partial", "events");
		model.addAttribute("pageTitle", "Events");
		model.addAttribute("jsonLink", "admin/events/all");
		return "index";
	}
	
	// request by json all places
	@RequestMapping(path="/all", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getAllTimetable( @RequestParam("length") int length,@RequestParam("draw") int draw, @RequestParam("start") int start) {
		
		List<Timetable> timetableList = timetableService.findTimetable(start, length);
		Long count = timetableService.countGet();
		
		
		
		Map<String, Object> data = new HashMap<>();
		data.put("draw",draw);
		data.put("recordsTotal", count);
		data.put("recordsFiltered", count);
		data.put("data",timetableList);
	    
	    return data;
	}
	
	
//	// request by json all places
//	@RequestMapping(path="/all", method=RequestMethod.GET, produces = "application/json")
//	@ResponseBody
//	public Map<String, Object> getAllTimetable() {
//		
//		List<Timetable> timetableList = timetableService.findAllTimetable();
//		
//		System.out.println("timetable este " + timetableList.toString());
//		
//		Map<String, Object> data = new HashMap<>();
//			data.put("data",timetableList);
//	    
//	    return data;
//	}
	

}