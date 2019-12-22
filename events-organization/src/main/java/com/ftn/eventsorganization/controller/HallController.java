package com.ftn.eventsorganization.controller;

import com.ftn.eventsorganization.DTO.HallDTO;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Hall;
import com.ftn.eventsorganization.service.IHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hall")
public class HallController {

    @Autowired
    IHallService hallService;

    @GetMapping("")
    public ResponseEntity<List<Hall>> findAll() {

        List<Hall> halls = hallService.findAll();

        return new ResponseEntity<>(halls, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hall> findById(@PathVariable Long id) throws ObjectNotFoundException {

        Hall hall = hallService.getOne(id);

        return new ResponseEntity<>(hall, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Hall> create(@RequestBody HallDTO dto) throws Exception {

        Hall hall = hallService.create(dto);

        return new ResponseEntity<>(hall, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Hall> update(@RequestBody Hall hallUpdate) throws ObjectNotFoundException {

        Hall hall = hallService.update(hallUpdate);

        return new ResponseEntity<>(hall, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ObjectNotFoundException {

        boolean deleted = hallService.delete(id);

        return new ResponseEntity<>("Hall successfully deleted!", HttpStatus.OK);
    }
}
