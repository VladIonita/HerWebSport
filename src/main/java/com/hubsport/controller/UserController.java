package com.hubsport.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import com.hubsport.domain.Users;
import com.hubsport.security.UserFormValidator;
import com.hubsport.service.UserService;

@Controller
@RequestMapping(value = "/admin/users")
public class UserController {

	@Autowired
	UserFormValidator userFormValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userFormValidator);
	}

	@Autowired
	UserService userService;

	@Autowired
	MessageSource messageSource;

	// list all users
	@RequestMapping
	public String usersPage(ModelMap model) {
		model.addAttribute("userList", userService.findAllUsers());
		model.addAttribute("pageTitle", "Users");
		model.addAttribute("partial", "users");
		return "index";
	}

	// show add user form
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String showAddUserForm(Model model) {
		Users users = new Users();
		model.addAttribute("userForm", users);
		model.addAttribute("partial", "userform");
		return "index";
	}

	// save or update user
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveOrUpdateUser(@ModelAttribute("userForm") @Validated Users users, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model.addAttribute("partial", "userform");
			return "index";
		} else {
			redirectAttributes.addFlashAttribute("css", "success");
			if (users.isNew()) {
				redirectAttributes.addFlashAttribute("msg", "User added successfully!");
			} else {
				redirectAttributes.addFlashAttribute("msg", "User updated successfully!");
			}

			userService.saveOrUpdate(users);

			// POST/REDIRECT/GET
			return "redirect:/admin/users";

			// POST/FORWARD/GET
			// return "user/list";

		}

	}

	// show update form
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String showUpdateUserForm(@PathVariable("id") int id, Model model) {
		Users users = userService.findById(id);
		model.addAttribute("userForm", users);
		model.addAttribute("partial", "userform");
		return "index";
	}

	// delete user
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") int id) {
		userService.deleteBID(id);
		return "redirect:/admin/users";
	}

	//by ajax and json
	@RequestMapping(path = "/listOfJSON", method = RequestMethod.GET)
	public String goHome() {
		return "UsersByJSOn";
	}
}
