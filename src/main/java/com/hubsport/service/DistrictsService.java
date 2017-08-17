package com.hubsport.service;

import java.util.List;

import com.hubsport.domain.Districts;

public interface DistrictsService {
	
	List<Districts> findAllDistricts(); 
	
	Districts findById(Integer id);
    
    void saveDistricts(Districts districts);
     
    void deleteDistrictsById(Integer id);
	

}