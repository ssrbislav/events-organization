package com.ftn.eventsorganization.service;

import com.ftn.eventsorganization.DTO.RegistrationDTO;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.model.Visitor;
import org.hibernate.ObjectNotFoundException;

import java.io.InvalidObjectException;

public interface IVisitorService {

    Visitor create(RegistrationDTO dto) throws ObjectNotFoundException, InvalidInputException;
}
