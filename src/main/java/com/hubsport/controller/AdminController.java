package com.hubsport.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.hubsport.domain.User;
import com.hubsport.service.CurrentTimeFormated;
import com.hubsport.service.UserService;

@Controller
@RequestMapping("/")
public class AdminController {
	
	
	// pt a afisa direct pe prima pagina punem sus @SesionAtributes cu nume gen roles si jos un @ModelAtribute cu listarea in sine

	// daca inceerc sa ma loghez dupa ce m am delogt cu un user care abia a fost creat nu ma lasa eroare 405 
	@Autowired
	UserService userService;
	
	@Autowired
	CurrentTimeFormated timeService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String loginPage(Model model) {
		return "loginPage";
	}

	 @RequestMapping(value = "/newuser", method = RequestMethod.GET)
	    public String newUser(ModelMap model) {
	        User user = new User();
	        model.addAttribute("date_week",timeService.time());
	        model.addAttribute("user", user);
	        model.addAttribute("edit", false);
	        model.addAttribute("loggedinuser", getPrincipal());
	        return "registration";
	    }

	 @RequestMapping(value = "/newuser", method = RequestMethod.POST)
	    public String saveUser(@Valid User user, BindingResult result,
	            ModelMap model) {
	 
	        if (result.hasErrors()) {
	            return "registration";
	        }
	        userService.saveUser(user);
	        model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
	        //return "success";
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
		model.addAttribute("date_week",timeService.time());
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
		model.addAttribute("date_week",timeService.time());
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registration";
    }
	
	// access to users
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String usersPage(ModelMap model) {
		model.addAttribute("date_week",timeService.time());
		List<User> users = userService.findAllUsers();
		model.addAttribute("userList", users);
		model.addAttribute("loggedinuser", getPrincipal());
		return "users";
	}

	// access to dashboard to administrate admin
	@RequestMapping(value="/admin" , method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		model.addAttribute("date_week",timeService.time());
		model.addAttribute("loggedinuser", getPrincipal());
		return "admin";
	}

	// handling 404 error
	@RequestMapping(value = "*", method = RequestMethod.GET)
	public String fallback(ModelMap model) {
		model.addAttribute("date_week",timeService.time());
		model.addAttribute("loggedinuser", getPrincipal());
		return "fallback";
	}

	
	// handling logout
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "loginPage";
	}

	//returning current user
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