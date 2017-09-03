package com.hubsport.service;

import java.util.List;

import com.hubsport.domain.Towns;

public interface TownsService {
	
	Towns findById(int id);
	
	Towns findByName(String name);
    
    void saveTowns(Towns towns);
     
    void deleteTownsById(Integer id);
	
	List<Towns> findAllTowns(); 
	
	List<Towns> findAllTowns(String query); 

}