package com.hubsport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hubsport.service.CurrentTimeFormated;

@Controller
@RequestMapping(value="/")
public class HomeController {
	


	@RequestMapping(value={"/login","/admin"}, method = RequestMethod.GET)
	public String loginPage() {
		return "loginPage";
	}
	
}


