package com.hubsport.service;

import java.util.List;

import com.hubsport.domain.Towns;

public interface TownsService {

	Towns findTownById(Integer id);

	Towns findTownByName(String name);

	void saveTown(Towns town);

	void deleteTownById(Integer id);

	List<Towns> findAllTowns();

	List<Towns> findAllTowns(String name);

}