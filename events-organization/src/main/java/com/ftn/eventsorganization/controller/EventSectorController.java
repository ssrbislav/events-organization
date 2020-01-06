package com.ftn.eventsorganization.controller;

import com.ftn.eventsorganization.DTO.EventSectorDTO;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.EventSector;
import com.ftn.eventsorganization.service.IEventSectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/event_sector", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventSectorController {

    @Autowired
    IEventSectorService service;

    @GetMapping("")
    public ResponseEntity<List<EventSector>> findAll() {

        List<EventSector> eventSectors = service.findAll();
        return new ResponseEntity<>(eventSectors, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<EventSector> findById(@PathVariable Long id) throws ObjectNotFoundException {

        try {
            EventSector es = service.getOne(id);
            return new ResponseEntity<>(es, HttpStatus.OK);
        } catch (ObjectNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<EventSector> create(@RequestBody EventSectorDTO dto) throws InvalidInputException, ObjectNotFoundException {

        try {
            EventSector es = service.create(dto);
            return new ResponseEntity<>(es, HttpStatus.OK);
        } catch (ObjectNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (InvalidInputException ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<EventSector> update(@RequestBody EventSector eventSector) throws InvalidInputException, ObjectNotFoundException {

        try {
            EventSector es = service.update(eventSector);
            return new ResponseEntity<>(es, HttpStatus.OK);
        } catch (ObjectNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (InvalidInputException ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws InvalidInputException, ObjectNotFoundException {

        return new ResponseEntity<>("Method not implemented!", HttpStatus.OK);
    }
}
