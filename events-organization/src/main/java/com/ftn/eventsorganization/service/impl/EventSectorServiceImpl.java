package com.ftn.eventsorganization.service.impl;

import com.ftn.eventsorganization.DTO.EventSectorDTO;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Event;
import com.ftn.eventsorganization.model.EventSector;
import com.ftn.eventsorganization.model.Sector;
import com.ftn.eventsorganization.repository.EventSectorRepository;
import com.ftn.eventsorganization.service.IEventSectorService;
import com.ftn.eventsorganization.service.IEventService;
import com.ftn.eventsorganization.service.ISectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventSectorServiceImpl implements IEventSectorService {

    @Autowired
    EventSectorRepository repository;

    @Autowired
    ISectorService sectorService;

    @Autowired
    IEventService eventService;

    @Override
    public EventSector getOne(Long id) throws ObjectNotFoundException {

        return repository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("EventSector with id - " + id + " does not exist!"));
    }

    @Override
    public List<EventSector> findAll() {

        return repository.findAll();
    }

    @Override
    public List<EventSector> findAllBySector(Long sectorId) throws ObjectNotFoundException {

        Sector sector = sectorService.getOne(sectorId);

        return repository.findAllBySector(sector);
    }

    @Override
    public List<EventSector> findAllByEvent(Long eventId) throws ObjectNotFoundException {

        Event event = eventService.getOne(eventId);

        return repository.findAllByEvent(event);
    }

    @Override
    public EventSector create(EventSectorDTO dto) throws InvalidInputException, ObjectNotFoundException {
        Optional<EventSector> es;
        try {
            if(repository.findByEventIdAndSectorId(dto.getEventId(), dto.getSectorId()).isPresent()) {
                System.out.println("JEBiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii!!!!!!!!!!*********************************");
                throw new ObjectNotFoundException("found");
            }
            es = Optional.of(new EventSector());
            try {
                Sector sector = sectorService.getOne(dto.getSectorId());
                es.get().setSector(sector);
            } catch (ObjectNotFoundException ex) {
                throw new ObjectNotFoundException("sector");
            }
            try {
                Event event = eventService.getOne(dto.getEventId());
                es.get().setEvent(event);
            } catch (ObjectNotFoundException ex) {
                throw new ObjectNotFoundException("event");
            }
            if (dto.getPrice() < 0)
                throw new InvalidInputException("price");
            es.get().setPrice(dto.getPrice());
            es.get().setSectorType(dto.getSectorType());
            return repository.save(es.get());
        } catch (ObjectNotFoundException ex) {
            if (ex.getMessage().equals("sector")) {
                throw new ObjectNotFoundException("Sector not found!");
            }
            else if(ex.getMessage().equals("found")) {
                throw new ObjectNotFoundException("Entity with same Event and Sector already created.");
            }
            throw new ObjectNotFoundException("Event not found!");
        } catch (InvalidInputException ex) {
            throw new InvalidInputException("Price must be positive number!");
        }
    }

    @Override
    public EventSector update(EventSector location) throws ObjectNotFoundException {
        return null;
    }

    @Override
    public boolean delete(Long id) throws ObjectNotFoundException {
        // OVAJ METOD NAM VEROVATNO NE TREBA
        return true;
    }
}
