package com.ftn.eventsorganization.controller;

import com.ftn.eventsorganization.DTO.SectorDTO;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Sector;
import com.ftn.eventsorganization.service.ISectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/sector", produces = MediaType.APPLICATION_JSON_VALUE)
public class SectorController {

    @Autowired
    ISectorService sectorService;

    @GetMapping("")
    public ResponseEntity<List<Sector>> findAll() {

        List<Sector> sectors = sectorService.findAll();

        return new ResponseEntity<>(sectors, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Sector> findById(@PathVariable Long id) throws ObjectNotFoundException {

        Sector sector = sectorService.getOne(id);

        return new ResponseEntity<>(sector, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Sector> create(@RequestBody SectorDTO dto) throws InvalidInputException, ObjectNotFoundException {

        Sector sector = sectorService.create(dto);

        return new ResponseEntity<>(sector, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<Sector> update(@RequestBody Sector sec) throws InvalidInputException, ObjectNotFoundException {

        Sector sector = sectorService.update(sec);

        return new ResponseEntity<>(sector, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ObjectNotFoundException {

        boolean deleted = sectorService.delete(id);

        return new ResponseEntity<>("Sector deleted successfully", HttpStatus.OK);
    }

}
