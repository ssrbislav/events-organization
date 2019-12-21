package com.ftn.eventsorganization.service.impl;

import com.ftn.eventsorganization.DTO.LocationDTO;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Location;
import com.ftn.eventsorganization.repository.LocationRepository;
import com.ftn.eventsorganization.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService implements ILocationService {

    @Autowired
    LocationRepository locationRepository;

    @Override
    public Location getOne(Long id) throws ObjectNotFoundException {
        return locationRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Location with id - " + id + " does not exist!"));
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAllByDeletedIsFalse();
    }

    @Override
    public Location create(LocationDTO dto) throws InvalidInputException {
        Optional<Location> location;
        try {
            if (!locationRepository.findByName(dto.getName()).isPresent()) {
                location = Optional.of(new Location());
                location.get().setCity(dto.getCity());
                location.get().setCountry(dto.getCountry());
                location.get().setName(dto.getName());
                location.get().setNumber(dto.getNumber());
                location.get().setStreetName(dto.getStreetName());
                location.get().setZipCode(dto.getZipCode());
            } else if (dto.getName() == null) {
                throw new InvalidInputException("present");
            } else {
                throw new InvalidInputException("unique");
            }
            return locationRepository.save(location.get());
        } catch (InvalidInputException ex) {
            if (ex.getMessage().contains("present")) {
                throw new InvalidInputException("Name must be present!");
            } else {
                throw new InvalidInputException("Location with the same name already exist!");
            }
        }
    }

    @Override
    public Location update(Location loc) throws ObjectNotFoundException {
        Optional<Location> location;
        try {
            location = Optional.ofNullable(getOne(loc.getId()));
            if (location.isPresent() && !location.get().isDeleted()) {
                location.get().setZipCode(loc.getZipCode());
                location.get().setStreetName(loc.getStreetName());
                location.get().setNumber(loc.getNumber());
                location.get().setName(loc.getName());
                location.get().setCountry(loc.getCountry());
                location.get().setCity(loc.getCity());
                return locationRepository.save(location.get());
            } else {
                throw new ObjectNotFoundException();
            }
        } catch (ObjectNotFoundException ex) {
            ex.printStackTrace();
            throw new ObjectNotFoundException("Location does not exist!", ex);
        }
    }

    @Override
    public boolean delete(Long id) throws ObjectNotFoundException {
        try {
            Location location = getOne(id);
            location.setDeleted(true);
            locationRepository.save(location);
            return location.isDeleted();
        } catch (ObjectNotFoundException ex) {
            ex.printStackTrace();
            throw new ObjectNotFoundException("Location not found!", ex);
        }
    }
}
