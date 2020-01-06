package com.ftn.eventsorganization.service;

import com.ftn.eventsorganization.DTO.HallDTO;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Hall;

import java.util.List;

public interface IHallService {

    List<Hall> findAll();

    Hall getOne(Long id) throws ObjectNotFoundException;

    Hall create(HallDTO hall) throws ObjectNotFoundException, InvalidInputException;

    Hall update(Hall hall) throws ObjectNotFoundException;

    boolean delete(Long id) throws ObjectNotFoundException;
}
