package com.ftn.eventsorganization.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.eventsorganization.dto.LocationDTO;
import com.ftn.eventsorganization.model.Location;
import com.ftn.eventsorganization.service.LocationService;

@RestController
@RequestMapping(value = "/location", produces = MediaType.APPLICATION_JSON_VALUE)
public class LocationController {

	@Autowired
	private LocationService locationService;
	
	@GetMapping(value = "/{id}") 
	public ResponseEntity<Location> getLocationById(@PathVariable Long id){
		Location location = locationService.findById(id);
		return ResponseEntity.ok().body(location);
	}
	
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<Location>> getAllLocations(){
		List <Location> locations = locationService.getAll();
		return ResponseEntity.ok().body(locations);
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity<Location> createLocation(@RequestBody LocationDTO dto){
		Location location = locationService.create(dto);
		return ResponseEntity.ok().body(location);
	} 
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Location> deleteLocation(@PathVariable Long id){
		Location location = locationService.delete(id);
		return ResponseEntity.ok().body(location);
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody LocationDTO dto){
		Location location = locationService.update(id, dto);
		return ResponseEntity.ok().body(location);
		
	}
	
}
