package com.ftn.eventsorganization.service;

import com.ftn.eventsorganization.DTO.EventDTO;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Event;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IEventService {

    List<Event> findAll();
    Event getOne(Long id) throws ObjectNotFoundException;
    Event create(EventDTO dto) throws ObjectNotFoundException, InvalidInputException;
    Event update(Event event) throws ObjectNotFoundException;
    boolean delete(Long id) throws ObjectNotFoundException;
}
