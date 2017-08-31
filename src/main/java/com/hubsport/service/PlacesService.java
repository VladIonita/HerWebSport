package com.hubsport.service;

import java.util.List;

import com.hubsport.domain.Places;

public interface PlacesService {
	
    Places findById(Integer id);
    
    void savePlace(Places places);
     
    void updatePlace(Places places);
     
    void deletePlacesById(Integer id);
 
    List<Places> findAllPlaces();
    
    List<Places> findPlaces(Integer start, Integer lenght); 
    
    List<Places> findPlaces(String district, Integer start, Integer lenght);
    
    Long countGet();
    
}