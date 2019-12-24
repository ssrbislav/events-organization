package com.ftn.eventsorganization.service.impl;

import com.ftn.eventsorganization.DTO.HallDTO;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Hall;
import com.ftn.eventsorganization.model.Location;
import com.ftn.eventsorganization.repository.HallRepository;
import com.ftn.eventsorganization.service.IHallService;
import com.ftn.eventsorganization.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HallServiceImpl implements IHallService {

    @Autowired
    HallRepository hallRepository;

    @Autowired
    ILocationService locationService;

    @Override
    public List<Hall> findAll() {
        return hallRepository.findAllByDeletedIsFalse();
    }

    @Override
    public Hall getOne(Long id) throws ObjectNotFoundException {
        return hallRepository.findByIdAndDeletedIsFalse(id).orElseThrow(() ->
                new ObjectNotFoundException("Hall with id - " + id + " does not exist!"));
    }

    @Override
    public Hall create(HallDTO dto) throws InvalidInputException, ObjectNotFoundException {
        Optional<Hall> hall;
        Location location;
        try {
            hall = Optional.of(new Hall());
            try {
                location = locationService.getOne(dto.getLocationId());
                hall.get().setLocation(location);
            } catch (ObjectNotFoundException ex) {
                throw new ObjectNotFoundException();
            }
            if(dto.getName().equals("")) {
                throw new InvalidInputException();
            }
            hall.get().setName(dto.getName());
            return hallRepository.save(hall.get());
        } catch (InvalidInputException ex) {
            throw new InvalidInputException("Name must be present!", ex);
        } catch (ObjectNotFoundException ex) {
            throw new ObjectNotFoundException("Location not found");
        }
    }

    @Override
    public Hall update(Hall hallUpdate) throws ObjectNotFoundException {
        Optional<Hall> hall;
        try {
            hall = Optional.ofNullable(getOne(hallUpdate.getId()));
            if(hall.isPresent() && !hall.get().isDeleted()) {
                hall.get().setName(hallUpdate.getName());
            } else {
                throw new ObjectNotFoundException();
            }
            return hallRepository.save(hall.get());
        } catch (ObjectNotFoundException ex) {
            throw new ObjectNotFoundException("Hall does not exist!");
        }
    }

    @Override
    public boolean delete(Long id) throws ObjectNotFoundException {
        try {
            Hall hall = getOne(id);
            hall.setDeleted(true);
            hallRepository.save(hall);
            return hall.isDeleted();
        } catch (ObjectNotFoundException ex) {
            throw new ObjectNotFoundException("Hall not found!");
        }
    }
}
