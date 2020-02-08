package com.ftn.eventsorganization.service;

import java.util.List;

import com.ftn.eventsorganization.DTO.TicketDto;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.EventSector;

public interface TicketService {

	boolean reservation(List<TicketDto> tickets, String username) throws ObjectNotFoundException, InvalidInputException;
	boolean ticketExist(EventSector es, int c, int r);
	boolean cancelReservation(Long id) throws ObjectNotFoundException;
	boolean buy(Long id) throws ObjectNotFoundException;
}
