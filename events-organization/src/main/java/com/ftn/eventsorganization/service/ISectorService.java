package com.ftn.eventsorganization.service;

import com.ftn.eventsorganization.DTO.SectorDTO;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Sector;

import java.util.List;

public interface ISectorService {

    List<Sector> findAll();

    Sector getOne(Long id) throws ObjectNotFoundException;

    Sector create(SectorDTO dto) throws InvalidInputException, ObjectNotFoundException;

    Sector update(Sector sector) throws ObjectNotFoundException, InvalidInputException;

    boolean delete(Long id) throws ObjectNotFoundException;
}
