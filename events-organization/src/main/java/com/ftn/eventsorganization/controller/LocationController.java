package com.ftn.eventsorganization.controller;

import com.ftn.eventsorganization.DTO.LocationDTO;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Location;
import com.ftn.eventsorganization.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/location", produces = MediaType.APPLICATION_JSON_VALUE)
public class LocationController {

    @Autowired
    ILocationService locationService;

    @GetMapping("")
    public ResponseEntity<List<Location>> findAllLocations() {

        List<Location> locations = locationService.findAll();

        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> findOneById(@PathVariable Long id) throws ObjectNotFoundException {

        Location location = locationService.getOne(id);

        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Location> createLocation(@RequestBody LocationDTO dto) throws InvalidInputException {

        Location location = locationService.create(dto);

        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Location> updateLocation(@RequestBody Location loc) throws ObjectNotFoundException {

        Location location = locationService.update(loc);

        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable Long id) throws ObjectNotFoundException {

        boolean deleted = locationService.delete(id);

        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }

}
