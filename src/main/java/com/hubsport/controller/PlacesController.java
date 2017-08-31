package com.hubsport.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hubsport.domain.Districts;
import com.hubsport.domain.Places;
import com.hubsport.domain.Users;
import com.hubsport.service.DistrictsService;
import com.hubsport.service.PlacesService;
import com.hubsport.service.TownsService;

@Controller
@RequestMapping(value = "/admin/places")
public class PlacesController {

	@Autowired
	PlacesService placesService;

	@Autowired
	TownsService townsService;
	
	@Autowired
	DistrictsService districService;
	
	@Autowired
	MessageSource messageSource;

	@RequestMapping
	public String placesPage(ModelMap model) {
		model.addAttribute("distList", districService.findAllDistricts());
		model.addAttribute("partial", "places");
		model.addAttribute("pageTitle", "Places");
			model.addAttribute("jsonLink", "admin/places/all");
		return "index";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String placesPage2(ModelMap model, @RequestParam String district) {
		model.addAttribute("distList", districService.findAllDistricts());
		model.addAttribute("partial", "places");
		model.addAttribute("pageTitle", "Places");
		System.out.println(district);
		model.addAttribute("jsonLink", "admin/places/all/" + district);
		return "index";
	}
	
	// show add place form
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String showAddPlacesForm(Model model) {
		Places places = new Places();
		model.addAttribute("placeForm", places);
		model.addAttribute("townList", townsService.findAllTowns());
		model.addAttribute("distList", districService.findAllDistricts());
		model.addAttribute("partial", "placesform");
		return "index";
	}
	
	
	// save  place
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveOrUpdatePlaces(@ModelAttribute("placeForm") @Validated Places places,
			BindingResult result, Model model) {
		model.addAttribute("districtList", districService.findAllDistricts());
		if (result.hasErrors()) {
			model.addAttribute("partial", "placesform");
			return "index";
		}
		placesService.savePlace(places);
		return "redirect:/admin/places";
	}

	// show update form
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String showUpdatePlacesForm(@PathVariable("id") int id, Model model) {
		Places places = placesService.findById(id);
		model.addAttribute("placeForm", places);
		model.addAttribute("partial", "placesform");
		return "index";
	}

	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updatePlaces(@PathVariable("id") int id, Model model, @Validated	Places places,
			BindingResult result) {

		if (result.hasErrors()) {
			model.addAttribute("partial", "placesform");
			return "index";
		}

		placesService.updatePlace(places);
		return "redirect:/admin/places";
	}
	
	// delete place
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deletePlaces(@PathVariable("id") int id) {
		placesService.deletePlacesById(id);
		return "redirect:/admin/places";
	}
	
	// request by json all places
	@RequestMapping(path="/all", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getAllPlaces( @RequestParam("length") int length,@RequestParam("draw") int draw, @RequestParam("start") int start) {
		
		List<Places> placesList = placesService.findPlaces(start, length);
		Long count = placesService.countGet();
		
		Map<String, Object> data = new HashMap<>();
		data.put("draw",draw);
		data.put("recordsTotal", count);
		data.put("recordsFiltered", count);
		data.put("data",placesList);
	    
	    return data;
	}
	
	// request by json all places
	@RequestMapping(path="/all/{district}", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getAllPlacesDistrict(@PathVariable("district") String district, @RequestParam("length") int length,@RequestParam("draw") int draw, @RequestParam("start") int start) {
		
		
		List<Places> placesList = placesService.findPlaces(district, start, length);
		Long count = placesService.countGet();
		
		Map<String, Object> data = new HashMap<>();
		data.put("draw",draw);
		data.put("recordsTotal", count);
		data.put("recordsFiltered", count);
		data.put("data",placesList);
	    
	    return data;
	}
}
