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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/location", produces = MediaType.APPLICATION_JSON_VALUE)
public class LocationController {

    @Autowired
    ILocationService locationService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("")
    public ResponseEntity<List<Location>> findAllLocations() {

        List<Location> locations = locationService.findAll();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Location> findOneById(@PathVariable Long id) throws ObjectNotFoundException {

        try {
            Location location = locationService.getOne(id);
            return new ResponseEntity<>(location, HttpStatus.OK);
        } catch (ObjectNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Location> createLocation(@RequestBody LocationDTO dto) throws InvalidInputException {

        try {
            Location location = locationService.create(dto);
            return new ResponseEntity<>(location, HttpStatus.OK);
        } catch (InvalidInputException ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<Location> updateLocation(@RequestBody Location loc) throws ObjectNotFoundException {

        try {
            Location location = locationService.update(loc);
            return new ResponseEntity<>(location, HttpStatus.OK);
        } catch (ObjectNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable Long id) throws ObjectNotFoundException {

        try {
            boolean deleted = locationService.delete(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (ObjectNotFoundException ex) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
