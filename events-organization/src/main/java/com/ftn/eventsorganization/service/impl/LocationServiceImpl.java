package com.ftn.eventsorganization.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.eventsorganization.dto.LocationDTO;
import com.ftn.eventsorganization.model.Location;
import com.ftn.eventsorganization.repository.LocationRepository;
import com.ftn.eventsorganization.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	LocationRepository locationRepository;

	@Override
	public Location findById(Long id) {
		
		return locationRepository.findById(id).get();
	}

	@Override
	public List<Location> getAll() {
		
		return locationRepository.findAll();
	}

	@Override
	public Location update(Long id,LocationDTO dto) {
		
		Location location =  locationRepository.findById(id).get();
		
		if (location != null)
		{
			location = locationDTOtoLocation(location, dto);
			return locationRepository.save(location);
		}
		
		return null;
		
	}

	@Override
	public Location delete(Long id) {
	
        Location location =  locationRepository.findById(id).get();
		
		if (location != null)
		{
			locationRepository.delete(location);
			return location;
		}
		
		return null;
	}

	@Override
	public Location create(LocationDTO dto) {
	
		Location loc=new Location();
		loc=locationDTOtoLocation(loc, dto);
		return locationRepository.save(loc);
	}
	
	private Location locationDTOtoLocation(Location location, LocationDTO locationDTO)
	{
		location.setName(locationDTO.getName());
		location.setNumber(locationDTO.getNumber());
		location.setStreetName(locationDTO.getStreetName());
		location.setCity(locationDTO.getCity());
		location.setZipCode(locationDTO.getZipCode());
		location.setCountry(locationDTO.getCountry());
		
		return location;
	}



}
