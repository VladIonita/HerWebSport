package com.hubsport.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hubsport.domain.Districts;
import com.hubsport.domain.Places;
import com.hubsport.domain.Towns;
import com.hubsport.domain.User;
import com.hubsport.service.CurrentTimeFormated;
import com.hubsport.service.DistrictsService;
import com.hubsport.service.PlacesService;
import com.hubsport.service.TownsService;
import com.hubsport.service.UserService;

@Controller
public class PlacesController {

	@Autowired
	PlacesService placesService;

	@Autowired
	TownsService townsService;

	@Autowired
	DistrictsService districtsService;
	
	@Autowired
	CurrentTimeFormated timeService;

	// access to Places
	@RequestMapping(value = "/places", method = RequestMethod.GET)
	public String placePage(ModelMap model) {
		model.addAttribute("date_week", timeService.time());
		List<Places> placesList = placesService.findAllPlaces();
		model.addAttribute("placesList", placesList);
		model.addAttribute("loggedinuser", getPrincipal());
		return "places";
	}

	@RequestMapping(value = "/newplace", method = RequestMethod.GET)
	public String newPlace(ModelMap model) {
		Places places = new Places();
		model.addAttribute("date_week", timeService.time());
		model.addAttribute("places", places);
		
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "registrationPlaces";
	}
	
	@ModelAttribute("townsList")
	public List<Towns> getTownsList() {
		List<Towns> townsList = townsService.findAllTowns();
		return townsList;
	}
	
	@ModelAttribute("districtsList")
	public List<Districts> getDistrictsList() {
		List<Districts> districtsList = districtsService.findAllDistricts();
		return districtsList;
	}
	

	@RequestMapping(value = "/newplace", method = RequestMethod.POST)
	public String saveUser(@Valid Places places, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "registrationPlaces";
		}
		placesService.savePlace(places);
		model.addAttribute("success",
				"Places " + places.getName() + " " + places.getAddress() + " registered successfully");
		return "registrationPlacesSuccess";
	}

	// This method will delete a place by it's id value.
	@RequestMapping(value = { "/delete-user-{id}" }, method = RequestMethod.GET)
	public String deletePlace(@PathVariable Integer id) {
		placesService.deletePlacesById(id);
		return "redirect:/places";
	}

	// This method will be called on form submission, handling POST request for
	// updating place in database. It also validates the place input
	@RequestMapping(value = { "/edit-user-{id}" }, method = RequestMethod.POST)
	public String updateUser(@Valid Places places, BindingResult result, ModelMap model, @PathVariable Integer id) {
		model.addAttribute("date_week", timeService.time());
		if (result.hasErrors()) {
			return "registrationPlaces";
		}
		placesService.updatePlace(places);
		model.addAttribute("success",
				"Places " + places.getName() + " " + places.getAddress() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "registrationPlacesSuccess";
	}

	// This method will provide the medium to update an existing place.
	@RequestMapping(value = { "/edit-user-{id}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable Integer id, ModelMap model) {
		model.addAttribute("date_week", timeService.time());
		Places places = placesService.findById(id);
		model.addAttribute("places", places);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "registrationPlaces";
	}

	// returning current user
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
