package com.hubsport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hubsport.service.CurrentTimeFormated;


@Controller
@RequestMapping(value="/admin")
@SessionAttributes(value={"loggedinuser","dateweek"})
public class AdminController {
	
	@Autowired
	CurrentTimeFormated timeService;

	@RequestMapping("/")
	public String defaultPage(Model model) {
        model.addAttribute("partial","bash");
		return "index";
	}
	
	// handling 404 error
	@RequestMapping(value = "*", method = {RequestMethod.GET, RequestMethod.POST})
	public String fallback() {
		return "fallback";
	}
	
	//returning current user
    @ModelAttribute("loggedinuser")
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
    
    @ModelAttribute("dateweek")
    private String getTime() {
    	String time = timeService.time();
    	return time;
    }
}