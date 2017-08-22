package com.hubsport.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.hubsport.service.UserService;

@Controller
@RequestMapping(value = "/")
public class HomeController {

	@Autowired
	UserService userService;
	
	@Autowired
	MessageSource messageSource;

	// handling 404 error
	@RequestMapping(value = "*", method = { RequestMethod.GET, RequestMethod.POST })
	public String fallback() {
		return "fallback";
	}
	
	// handling logout
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/admin/login";
	}
	
	// handling access to login
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
	return "redirect:/admin/login";
	}

	// handling access to admin
	@RequestMapping(value = "admin" , method = RequestMethod.GET)
	public String adminPage() {
	return "redirect:/admin/";
	}
}
