package com.ftn.eventsorganization.service.impl;

import com.ftn.eventsorganization.DTO.EventDTO;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Event;
import com.ftn.eventsorganization.model.Location;
import com.ftn.eventsorganization.repository.EventRepository;
import com.ftn.eventsorganization.service.IEventService;
import com.ftn.eventsorganization.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class EventServiceImpl implements IEventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ILocationService locationService;

    @Override
    public List<Event> findAll() {
        return eventRepository.findAllByDeletedIsFalse();
    }

    @Override
    public Event getOne(Long id) throws ObjectNotFoundException {
        return eventRepository.findByIdAndDeletedIsFalse(id).orElseThrow(() ->
                new ObjectNotFoundException("Event with id - " + id + " does not exist!"));
    }

    @Override
    public Event create(EventDTO dto) throws ObjectNotFoundException, InvalidInputException {
        Optional<Event> event;
        Optional<Location> location;

        try {
            location = Optional.ofNullable(locationService.getOne(dto.getLocationId()));
            if (location == null) {
                throw new ObjectNotFoundException("exist");
            }
            if (dto.getEndDate().before(dto.getStartDate())) {
                throw new InvalidInputException("before");
            }
            if (dto.getStartDate().before(new Date())) {
                throw new InvalidInputException("date");
            }
            if (!eventRepository.findByNameAndDeletedIsFalse(dto.getName()).isPresent()) {
                event = Optional.of(new Event());
                event.get().setName(dto.getName());
                event.get().setEventType(dto.getEventType());
                event.get().setStartDate(dto.getStartDate());
                event.get().setEndDate(dto.getEndDate());
                event.get().setLocation(location.get());
                return eventRepository.save(event.get());
            } else if (dto.getName().equals("")) {
                throw new InvalidInputException("present");
            } else {
                throw new InvalidInputException("unique");
            }
        } catch (InvalidInputException ex) {
            if (ex.getMessage().contains("present")) {
                throw new InvalidInputException("Name must be present!");
            } else if (ex.getMessage().contains("before")) {
                throw new InvalidInputException("EndDate must be after StartDate");
            } else if (ex.getMessage().contains("date")) {
                throw new InvalidInputException("StartDate must be after today");
            } else {
                throw new InvalidInputException("Event with the same name already exist!");
            }
        } catch (ObjectNotFoundException ex) {
            throw new ObjectNotFoundException("Location does not exist!");
        }
    }

    @Override
    public Event update(Event ev) throws ObjectNotFoundException {
        Optional<Event> event;
        try {
            event = Optional.ofNullable(getOne(ev.getId()));
            if (event.isPresent() && !event.get().isDeleted()) {
                event.get().setName(ev.getName());
                event.get().setStartDate(ev.getStartDate());
                event.get().setEndDate(ev.getEndDate());
                return eventRepository.save(event.get());
            } else {
                throw new ObjectNotFoundException();
            }
        } catch (ObjectNotFoundException ex) {
            throw new ObjectNotFoundException("Event does not exist!", ex);
        }
    }

    @Override
    public boolean delete(Long id) throws ObjectNotFoundException {
        try {
            Event event = getOne(id);
            event.setDeleted(true);
            eventRepository.save(event);
            return event.isDeleted();
        } catch (ObjectNotFoundException ex) {
            throw new ObjectNotFoundException("Requested event does not exist!", ex);
        }
    }
}
