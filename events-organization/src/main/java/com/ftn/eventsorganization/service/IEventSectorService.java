package com.ftn.eventsorganization.service;

import com.ftn.eventsorganization.DTO.EventSectorDTO;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.EventSector;

import java.util.List;

public interface IEventSectorService {

    EventSector getOne(Long id) throws ObjectNotFoundException;

    List<EventSector> findAll();

    List<EventSector> findAllBySector(Long sectorId) throws ObjectNotFoundException;

    List<EventSector> findAllByEvent(Long eventId) throws ObjectNotFoundException;

    EventSector create(EventSectorDTO es) throws InvalidInputException, ObjectNotFoundException;

    EventSector update(EventSector es) throws ObjectNotFoundException, InvalidInputException;

    boolean delete(Long id) throws ObjectNotFoundException;
}
