package com.ftn.eventsorganization.service;

import java.util.List;

import com.ftn.eventsorganization.dto.LocationDTO;
import com.ftn.eventsorganization.model.Location;



public interface LocationService {
	
	Location findById(Long id);
	
	List<Location> getAll();
	
	Location update(Long id,LocationDTO dto);
	
	Location delete(Long id);
	
	Location create(LocationDTO dto);
	
}
