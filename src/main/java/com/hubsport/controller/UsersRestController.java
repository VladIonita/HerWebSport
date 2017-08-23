package com.hubsport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hubsport.domain.Users;
import com.hubsport.service.UserService;

@Controller
@RequestMapping("/admin/json")
public class UsersRestController {	

	@Autowired
	UserService userService;
	
	@RequestMapping(path="/all", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Users> getAllUsers() {
		return userService.findAllUsers();
	}
	
	@RequestMapping(value="/all/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Users getUserById(@PathVariable Integer id) {
		return userService.findById(id);
	}
}
