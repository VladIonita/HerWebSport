package com.hubsport.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;

import com.hubsport.dao.FormValidationGroup;
import com.hubsport.domain.User;
import com.hubsport.service.CurrentTimeFormated;
import com.hubsport.service.UserService;

@Controller
@RequestMapping(value = "/admin/users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	MessageSource messageSource;

	// access to users
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String usersPage(ModelMap model) {
		model.addAttribute("userList", userService.findAllUsers());
		model.addAttribute("partial", "users");
		return "index";
	}

	// show add user form
	@RequestMapping(value = "/users/add", method = RequestMethod.GET)
	public String showAddUserForm(Model model) {
		User user = new User();
		model.addAttribute("userForm", user);
		model.addAttribute("partial", "userform");
		return "index";
	}

	// save or update user
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public String saveOrUpdateUser(@ModelAttribute("userForm") @Validated(FormValidationGroup.class) User user,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("partial", "userform");
			return "index";
		}

		if (!userService.isUserUnique(user.getId(), user.getUsername())) {
			FieldError usernameError = new FieldError("user", "username", messageSource
					.getMessage("non.unique.username", new String[] { user.getUsername() }, Locale.getDefault()));
			result.addError(usernameError);
			model.addAttribute("partial", "userform");
			return "index";

		} else if (!userService.isUserEmailUnique(user.getId(), user.getEmail())) {
			FieldError emailError = new FieldError("user", "email", messageSource.getMessage("non.unique.email",
					new String[] { user.getEmail() }, Locale.getDefault()));
			result.addError(emailError);
			model.addAttribute("partial", "userform");
			return "index";
		}
		userService.saveUser(user);
		return "redirect:/admin/users/list";
	}

	// show update form
	@RequestMapping(value = "/users/{id}/update", method = RequestMethod.GET)
	public String showUpdateUserForm(@PathVariable("id") int id, Model model) {
		User user = userService.findById(id);
		model.addAttribute("userForm", user);
		model.addAttribute("partial", "userform");
		return "index";
	}

	@RequestMapping(value = "/users/{id}/update", method = RequestMethod.POST)
	public String updateUser(@PathVariable("id") int id, Model model, @Validated(FormValidationGroup.class) User user,
			BindingResult result) {

		if (result.hasErrors()) {
			model.addAttribute("partial", "userform");
			return "index";
		}
		if (!userService.isUserUnique(user.getId(), user.getUsername())) {
			FieldError usernameError = new FieldError("user", "username", messageSource
					.getMessage("non.unique.username", new String[] { user.getUsername() }, Locale.getDefault()));
			result.addError(usernameError);
			model.addAttribute("partial", "userform");
			return "index";

		} else if (!userService.isUserEmailUnique(user.getId(), user.getEmail())) {
			FieldError emailError = new FieldError("user", "email", messageSource.getMessage("non.unique.email",
					new String[] { user.getEmail() }, Locale.getDefault()));
			result.addError(emailError);
			model.addAttribute("partial", "userform");
			return "index";
		}
		userService.updateUser(user);
		return "redirect:/admin/users/list";
	}

	// delete user
	@RequestMapping(value = "/users/{id}/delete", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") int id) {
		userService.deleteBID(id);
		return "redirect:/admin/users/list";
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
		model.addAttribute("partial", "userform");
		return "index";
	}

	// // This method will be called on form submission, handling POST request
	// for
	// // updating user in database. It also validates the user input
	// @RequestMapping(value = { "/edit-user-{id}" }, method =
	// RequestMethod.POST)
	// public String updateUser(@Valid User user, BindingResult result, ModelMap
	// model, @PathVariable String username) {
	//
	// Integer id = user.getId();
	// if (result.hasErrors()) {
	// model.addAttribute("partial","registration");
	// return "index";
	// }
	//
	// userService.updateUser(user);
	// model.addAttribute("partial","registrationsuccess");
	// model.addAttribute("success",
	// "User " + user.getFirstName() + " " + user.getLastName() + " updated
	// successfully");
	// return "index";
	// }

	//
	// @RequestMapping(value = { "/users/add" }, method = RequestMethod.POST)
	// public String saveOrUpdateUser(@Valid User user, BindingResult result,
	// ModelMap model) {
	//
	// if (result.hasErrors()) {
	// return "userform";
	// }
	//
	//// if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
	//// FieldError ssoError =new
	// FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId",
	// new
	////
	//// String[]{user.getSsoId()}, Locale.getDefault()));
	//// result.addError(ssoError);
	//// return "registration";
	//// }
	//
	// userService.saveOrUpdate(user);
	//
	// model.addAttribute("success", "User " + user.getFirstName() + " "+
	// user.getLastName() + " registered successfully");
	// //return "success";
	// return "redirect:/admin/users/list";
	// }

}
