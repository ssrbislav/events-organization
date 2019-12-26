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

        EventSector es = service.getOne(id);

        return new ResponseEntity<>(es, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<EventSector> create(@RequestBody EventSectorDTO dto) throws InvalidInputException, ObjectNotFoundException {

        EventSector es = service.create(dto);

        return new ResponseEntity<>(es, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<EventSector> update(@RequestBody EventSector eventSector) throws InvalidInputException, ObjectNotFoundException {

        EventSector es = service.update(eventSector);

        return new ResponseEntity<>(es, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws InvalidInputException, ObjectNotFoundException {

        //boolean deleted = service.delete(id);

        return new ResponseEntity<>("Method not implemented!", HttpStatus.OK);
    }
}
