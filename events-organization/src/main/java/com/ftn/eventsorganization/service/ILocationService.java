package com.ftn.eventsorganization.service;

import com.ftn.eventsorganization.DTO.LocationDTO;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Location;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ILocationService {

    Location getOne(Long id) throws ObjectNotFoundException;

    List<Location> findAll();

    Location create(LocationDTO location) throws InvalidInputException;

    Location update(Location location) throws ObjectNotFoundException;

    boolean delete(Long id) throws ObjectNotFoundException;
}
