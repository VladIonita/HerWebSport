package com.hubsport.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;

import com.hubsport.dao.FormValidationGroup;
import com.hubsport.domain.Users;
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
		Users users = new Users();
		model.addAttribute("userForm", users);
		model.addAttribute("partial", "userform");
		return "index";
	}

	// save or update user
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public String saveOrUpdateUser(@ModelAttribute("userForm") @Validated(FormValidationGroup.class) Users users,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("partial", "userform");
			return "index";
		}

		if (!userService.isUserUnique(users.getId(), users.getUsername())) {
			FieldError usernameError = new FieldError("user", "username", messageSource
					.getMessage("non.unique.username", new String[] { users.getUsername() }, Locale.getDefault()));
			result.addError(usernameError);
			model.addAttribute("partial", "userform");
			return "index";

		} else if (!userService.isUserEmailUnique(users.getId(), users.getEmail())) {
			FieldError emailError = new FieldError("user", "email", messageSource.getMessage("non.unique.email",
					new String[] { users.getEmail() }, Locale.getDefault()));
			result.addError(emailError);
			model.addAttribute("partial", "userform");
			return "index";
		}
		userService.saveUser(users);
		return "redirect:/admin/users/list";
	}

	// show update form
	@RequestMapping(value = "/users/{id}/update", method = RequestMethod.GET)
	public String showUpdateUserForm(@PathVariable("id") int id, Model model) {
		Users users = userService.findById(id);
		model.addAttribute("userForm", users);
		model.addAttribute("partial", "userform");
		return "index";
	}

	@RequestMapping(value = "/users/{id}/update", method = RequestMethod.POST)
	public String updateUser(@PathVariable("id") int id, Model model, @Validated(FormValidationGroup.class) Users users,
			BindingResult result) {

		if (result.hasErrors()) {
			model.addAttribute("partial", "userform");
			return "index";
		}
		if (!userService.isUserUnique(users.getId(), users.getUsername())) {
			FieldError usernameError = new FieldError("user", "username", messageSource
					.getMessage("non.unique.username", new String[] { users.getUsername() }, Locale.getDefault()));
			result.addError(usernameError);
			model.addAttribute("partial", "userform");
			return "index";

		} else if (!userService.isUserEmailUnique(users.getId(), users.getEmail())) {
			FieldError emailError = new FieldError("user", "email", messageSource.getMessage("non.unique.email",
					new String[] { users.getEmail() }, Locale.getDefault()));
			result.addError(emailError);
			model.addAttribute("partial", "userform");
			return "index";
		}
		userService.updateUser(users);
		return "redirect:/admin/users/list";
	}

	// delete user
	@RequestMapping(value = "/users/{id}/delete", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") int id) {
		userService.deleteBID(id);
		return "redirect:/admin/users/list";
	}

}
