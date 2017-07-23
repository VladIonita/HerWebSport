package com.hubsport.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hubsport.dao.FormValidationGroup;
import com.hubsport.dao.PersistenceValidationGroup;
import com.hubsport.domain.User;
import com.hubsport.service.UserService;

@Controller
public class AdminController {
	
	// pt a afisa direct pe prima pagina punem sus @SesionAtributes cu nume gen roles si jos un @ModelAtribute cu listarea in sine

	// treb sa creez o verificare pt dublarea de parola
	
	// treb sa creez o verificare ca fiecare din username, email, sa fie unique si daca nu e unique sa imi afiseze o eroare
	@Autowired
	UserService userService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String loginPage(Model model) {
		return "loginPage";
	}

	@RequestMapping(value = { "/registration" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}

	// This method will be called on form submission, handling POST request for
	// saving user in database. It also validates the user input
	@RequestMapping(value = { "/registration" }, method = RequestMethod.POST)
	public String saveUser(@Validated(FormValidationGroup.class) User user, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}
		if(!userService.isUserUnique(user.getUsername())){
			result.rejectValue("username", "DuplicateKey.user.username");
//            FieldError usernameError =new FieldError("user","username",messageSource.getMessage("non.unique.username", new String[]{user.getUsername()}, Locale.getDefault()));
//            result.addError(usernameError);
            return "registration";
        }else if(!userService.isUserEmailUnique(user.getEmail())){
            FieldError emailError =new FieldError("user","email",messageSource.getMessage("non.unique.email", new String[]{user.getEmail()}, Locale.getDefault()));
            result.addError(emailError);
            return "registration";
        } 
		
		userService.saveUser(user);

		model.addAttribute("success",
				"User " + user.getUsername() + " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		// return "success";
		return "registrationsuccess";
	}
	
     //This method will delete an user by it's username value.
    @RequestMapping(value = { "/delete-user-{username}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return "redirect:/users";
    }
    
//   This method will be called on form submission, handling POST request for updating user in database. It also validates the user input
    @RequestMapping(value = { "/edit-user-{username}" }, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result,
            ModelMap model, @PathVariable String username) {
 
        if (result.hasErrors()) {
            return "registration";
        }
 
        userService.updateUser(user);
 
        model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        return "registrationsuccess";
    } 
    
    
     // This method will provide the medium to update an existing user.
    @RequestMapping(value = { "/edit-user-{username}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable String username, ModelMap model) {
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registration";
    }
    
    

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		return "accessDenied";
	}

	// access to users
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String usersPage(ModelMap model) {
		List<User> users = userService.findAllUsers();
		model.addAttribute("userList", users);
		model.addAttribute("loggedinuser", getPrincipal());
		return "users";
	}

	// access to dashboard to administrate admin
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		List<User> users = userService.findAllUsers();
		model.addAttribute("userList", users);
		model.addAttribute("loggedinuser", getPrincipal());
		return "admin";
	}

	// handling 404 error
	@RequestMapping(value = "*", method = RequestMethod.GET)
	public String fallback() {
		return "fallback";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "loginPage";
	}

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
}
