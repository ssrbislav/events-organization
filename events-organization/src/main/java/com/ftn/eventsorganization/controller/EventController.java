package com.ftn.eventsorganization.controller;

import com.ftn.eventsorganization.DTO.EventDTO;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Event;
import com.ftn.eventsorganization.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/event", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventController {

    @Autowired
    IEventService eventService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {

        List<Event> events = eventService.findAll();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Event> findOne(@PathVariable Long id) throws ObjectNotFoundException {

        try {
            Event event = eventService.getOne(id);
            return new ResponseEntity<>(event, HttpStatus.OK);
        } catch (ObjectNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Event> create(@RequestBody EventDTO dto) throws InvalidInputException, ObjectNotFoundException {

        try {
            Event event = eventService.create(dto);
            return new ResponseEntity<>(event, HttpStatus.OK);
        } catch (ObjectNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (InvalidInputException ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<Event> update(@RequestBody Event ev) throws ObjectNotFoundException {

        try {
            Event event = eventService.update(ev);
            return new ResponseEntity<>(event, HttpStatus.OK);
        } catch (ObjectNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (InvalidInputException ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ObjectNotFoundException {

        try {
            boolean deleted = eventService.delete(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (ObjectNotFoundException ex) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
