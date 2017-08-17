package com.hubsport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String placesPage(ModelMap model) {
		model.addAttribute("timetableList", timetableService.findAllTimetable());
		model.addAttribute("partial", "events");
		return "index";
	}

}
