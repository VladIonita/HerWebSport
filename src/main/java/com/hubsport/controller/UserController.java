package com.hubsport.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.validation.BindingResult;

import com.hubsport.domain.User;
import com.hubsport.service.CurrentTimeFormated;
import com.hubsport.service.UserService;

@Controller
@RequestMapping(value = "/admin/users")
public class UserController {

	@Autowired
	UserService userService;
	
	// access to users
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String usersPage(ModelMap model) {
		List<User> users = userService.findAllUsers();
		model.addAttribute("userList", users);
        model.addAttribute("partial","users");
		return "index";
	}
	
	
	//new user returning page
	 @RequestMapping(value = "/newuser", method = RequestMethod.GET)
	    public String newUser(ModelMap model) {
	        User user = new User();
	        model.addAttribute("partial","registration");
	        model.addAttribute("user", user);
	        model.addAttribute("edit", false);
	        return "index";
	    }
	 
	 
	 
	//new user returning page
	@RequestMapping(value = "/newuser", method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
	        model.addAttribute("partial","registration");
			return "index";
		}
		userService.saveUser(user);
		return "redirect:/admin/users/";
	}
	
	

	// This method will delete an user by it's username value.
	@RequestMapping(value = { "/delete-user-{username}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String username) {
		userService.deleteUserByUsername(username);
		return "redirect:/admin/users/";
	}
	

	// This method will be called on form submission, handling POST request for
	// updating user in database. It also validates the user input
	@RequestMapping(value = { "/edit-user-{username}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable String username) {
		if (result.hasErrors()) {
	        model.addAttribute("partial","registration");
			return "index";
		}
		
		userService.updateUser(user);
        model.addAttribute("partial","registrationsuccess");
		model.addAttribute("success",
				"User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
		return "index";
	}

	
	
	// This method will provide the medium to update an existing user.
	@RequestMapping(value = { "/edit-user-{username}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String username, ModelMap model) {
		User user = userService.findByUsername(username);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
        model.addAttribute("partial","registration");
		return "index";
	}
	

}
