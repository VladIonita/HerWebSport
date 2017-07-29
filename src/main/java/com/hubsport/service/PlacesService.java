package com.hubsport.service;

import java.util.List;

import com.hubsport.domain.Places;
import com.hubsport.domain.Towns;

public interface PlacesService {
	
    Places findById(int id);
    
    void savePlace(Places places);
     
    void updatePlace(Places places);
     
    void deletePlacesById(Integer id);
 
    List<Places> findAllPlaces();
    
    List<Towns> findAllTowns();
    

}