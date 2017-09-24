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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hubsport.domain.Places;
import com.hubsport.domain.Towns;
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
		createModelPlacesPage(model);
		model.addAttribute("jsonLink", "admin/places/all");
		return "index";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String placesPageWithDistrict(ModelMap model, @RequestParam String district) {
		createModelPlacesPage(model);
		model.addAttribute("jsonLink", "admin/places/all/" + district);
		return "index";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String showAddPlacesForm(Model model) {
		Places places = new Places();
		model.addAttribute("placeForm", places);
		model.addAttribute("townList", townsService.findAllTowns());
		model.addAttribute("distList", districService.findAllDistricts());
		model.addAttribute("partial", "placesform");
		return "index";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveOrUpdatePlaces(@ModelAttribute("placeForm") @Validated Places places, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("partial", "placesform");
			return "index";
		}
		redirectAttributes.addFlashAttribute("css", "success");
		if (places.isNew()) {
			redirectAttributes.addFlashAttribute("msg", "Places added successfully!");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Places updated successfully!");
		}
		placesService.saveOrUpdatePlace(places);
		return "redirect:/admin/places";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String showUpdatePlacesForm(@PathVariable("id") int id, Model model) {
		Places places = placesService.findPlaceById(id);
		Towns towns = townsService.findTownById(places.getTowns().getid());
		model.addAttribute("id", places.getTowns().getid());
		model.addAttribute("placeForm", places);
		model.addAttribute("townsForm", towns);
		model.addAttribute("partial", "placesform");
		return "index";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deletePlaces(@PathVariable("id") int id) {
		placesService.deletePlaceById(id);
		return "redirect:/admin/places";
	}

	@RequestMapping(path = "/all", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getAllPlaces(@RequestParam("length") int length, @RequestParam("draw") int draw,
			@RequestParam("start") int start) {
		Long count = placesService.countGet();
		Map<String, Object> data = new HashMap<>();
		data.put("draw", draw);
		data.put("recordsTotal", count);
		data.put("recordsFiltered", count);
		data.put("data", placesService.findPlacesWithStartAndLength(start, length));
		return data;
	}

	@RequestMapping(path = "/all/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getAllPlacesDistrict(@PathVariable("id") Integer id, @RequestParam("length") int length,
			@RequestParam("draw") int draw, @RequestParam("start") int start) {
		Long count = placesService.countGet();
		Map<String, Object> data = new HashMap<>();
		data.put("draw", draw);
		data.put("recordsTotal", count);
		data.put("recordsFiltered", count);
		data.put("data", placesService.findPlacesWithIdStartAndLength(id, start, length));
		return data;
	}

	@RequestMapping(path = "/towns/{query}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Towns> getAllTownsByQuery(@PathVariable("query") String query) {
		List<Towns> townsList = townsService.findAllTowns(query);
		return townsList;
	}

	private void createModelPlacesPage(ModelMap model) {
		model.addAttribute("distList", districService.findAllDistricts());
		model.addAttribute("partial", "places");
		model.addAttribute("pageTitle", "Places");
	}
}
