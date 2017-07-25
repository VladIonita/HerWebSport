package com.hubsport.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hubsport.domain.User;
import com.hubsport.service.UserService;

@Controller
@RequestMapping("/")
public class EventsController {



	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		// List<User> users = userService.findAllUsers();
		// model.addAttribute("users", users);
		// model.addAttribute("loggedinuser", getPrincipal());
		return "home";
	}

	// @ModelAttribute("roles")
	// public List<User> initializeUsers() {
	// return userService.findAllUsers();
	// }

	// @RequestMapping(value = "/home", method = RequestMethod.GET)
	// public String homePage(Model model) {
	// return "home";
	// }

	
}
