package com.hubsport.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hubsport.domain.User;
import com.hubsport.service.UserService;

@Controller
public class EventsController {

	
	
	@Autowired
    UserService userService;
	
	
	
	// access to home page
//	@RequestMapping("/")
//	public String listObjects(Model model) {
//		model.addAttribute("eventsList", new Object());
//		return "home";
//	}
	
	
	@RequestMapping("/")
	public String listObjects(Model model) {
		
		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", getPrincipal());
		return "home";
	}
	
	
	

	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String loginPage(Model model) {
		return "loginPage";
	}

	// @RequestMapping(value = "/home", method = RequestMethod.GET)
	// public String homePage(Model model) {
	// return "home";
	// }

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		return "accessDenied";
	}

	// access to dashboard to administrate admin
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String dashboardPage() {
		return "admin";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String loginPage() {
		return "home";
	}
	
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       if (auth != null){    
          new SecurityContextLogoutHandler().logout(request, response, auth);
       }
       return "home";
    }
    
    
    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

}
