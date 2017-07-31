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
import org.springframework.validation.annotation.Validated;

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
		model.addAttribute("userList", userService.findAllUsers());
        model.addAttribute("partial","users");
		return "index";
	}
	
	// save or update user
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public String saveOrUpdateUser(@ModelAttribute("userForm") @Validated User user,
			BindingResult result, Model model) {

 		if (result.hasErrors()) {
	        model.addAttribute("partial","registration");
			return "index";
		} else {

			userService.saveOrUpdate(user);
			// POST/REDIRECT/GET
			return "redirect:/admin/users/";

			// POST/FORWARD/GET
			// return "user/list";
		}
	}

	// show add user form
	@RequestMapping(value = "/users/add", method = RequestMethod.GET)
	public String showAddUserForm(Model model) {
		 User user = new User();
	        model.addAttribute("partial","registration");
	        model.addAttribute("userForm", user);
	        model.addAttribute("edit", false);
	        return "index";
	}
	
	// show update form
	@RequestMapping(value = "/users/{id}/update", method = RequestMethod.GET)
	public String showUpdateUserForm(@PathVariable("id") int id, Model model) {
		User user = userService.findById(id);
		model.addAttribute("userForm", user);
		model.addAttribute("edit", true);
        model.addAttribute("partial","registration");
		return "index";
	}
	
	// delete user
	@RequestMapping(value = "/users/{id}/delete", method = RequestMethod.POST)
	public String deleteUser(@PathVariable("id") int id) {
		userService.delete(id);
		return "redirect:/admin/users/";
	}
	
	// show user
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public String showUser(@PathVariable("id") int id, Model model) {
		User user = userService.findById(id);
		if (user == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "User not found");
		}
		model.addAttribute("user", user);
        model.addAttribute("partial","registration");
		return "index";
	}
	
//	// This method will be called on form submission, handling POST request for
//	// updating user in database. It also validates the user input
//	@RequestMapping(value = { "/edit-user-{id}" }, method = RequestMethod.POST)
//	public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable String username) {
//		
//		Integer id = user.getId();
//		if (result.hasErrors()) {
//	        model.addAttribute("partial","registration");
//			return "index";
//		}
//		
//		userService.updateUser(user);
//        model.addAttribute("partial","registrationsuccess");
//		model.addAttribute("success",
//				"User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
//		return "index";
//	}

}
