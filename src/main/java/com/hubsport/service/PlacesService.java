package com.hubsport.service;

import java.util.List;

import com.hubsport.domain.Places;
import com.hubsport.domain.Towns;

public interface PlacesService {
	
    Places findById(Integer id);
    
    void saveOrUpdate(Places places);
     
    void deletePlacesById(Integer id);
 
    List<Places> findAllPlaces();
    
    List<Places> findPlaces(Integer start, Integer lenght); 
    
    List<Places> findPlaces(Integer id, Integer start, Integer lenght);
    
    Long countGet();
    
}