package com.hubsport.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public Map<String, Object> getAllUsers( @RequestParam("length") int length,@RequestParam("draw") int draw, @RequestParam("start") int start) {
		
		List<Users> usersList = userService.findUsers(start, length);
		Long count = userService.countGet();
		
		Map<String, Object> data = new HashMap<>();
		data.put("draw",draw);
		data.put("recordsTotal", count);
		data.put("recordsFiltered", count);
		data.put("data",usersList);
		
		
	    
	    return data;
	    
	}
	
	@RequestMapping(value="/all/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Users getUserById(@PathVariable Integer id) {
		return userService.findById(id);
	}
}
