package com.ftn.eventsorganization.Service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.eventsorganization.DTO.TicketDto;
import com.ftn.eventsorganization.enumeration.EventType;
import com.ftn.eventsorganization.enumeration.SectorType;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Event;
import com.ftn.eventsorganization.model.EventSector;
import com.ftn.eventsorganization.model.Hall;
import com.ftn.eventsorganization.model.Location;
import com.ftn.eventsorganization.model.Reservation;
import com.ftn.eventsorganization.model.Sector;
import com.ftn.eventsorganization.model.Ticket;
import com.ftn.eventsorganization.model.Visitor;
import com.ftn.eventsorganization.repository.EventRepository;
import com.ftn.eventsorganization.repository.EventSectorRepository;
import com.ftn.eventsorganization.repository.ReservationRepository;
import com.ftn.eventsorganization.repository.SectorRepository;
import com.ftn.eventsorganization.repository.TicketRepository;
import com.ftn.eventsorganization.repository.VisitorRepository;
import com.ftn.eventsorganization.service.IEventSectorService;
import com.ftn.eventsorganization.service.IEventService;
import com.ftn.eventsorganization.service.ISectorService;
import com.ftn.eventsorganization.service.IVisitorService;
import com.ftn.eventsorganization.service.TicketService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
@Rollback
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TicketServiceTest {
	
	@Autowired 
	TicketRepository ticketrepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	VisitorRepository visitorRepository;

	@Autowired
	IEventService eventService;

	@Autowired
	ISectorService sectorService;
	
	@Autowired 
	IEventSectorService esService;

	@Test
	public void testTicketDoesExist() throws ObjectNotFoundException {
		EventSector es = esService.getOne(1L);
		boolean value = ticketService.ticketExist(es, 1, 1);
		assertEquals(1, ticketrepository.getOne(1L).getSeat_column());
		assertEquals(1,ticketrepository.getOne(1L).getSeat_row());
	}
	
	@Test
	public void testTicketNotExist() throws ObjectNotFoundException{
		EventSector es = esService.getOne(1L);
		boolean value = ticketService.ticketExist(es, 2, 2);
		assertNotEquals(2, ticketrepository.getOne(1L).getSeat_column());
		assertNotEquals(2,ticketrepository.getOne(1L).getSeat_row());
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void testReservationNotCreatedSectorNotExist() throws ObjectNotFoundException, InvalidInputException{
		Reservation r = reservationRepository.getOne(1L);
		TicketDto t=new  TicketDto(1L,"SEC", 1, 1);
		List<TicketDto> tickets=new ArrayList<>();
		tickets.add(t);	
		String v = visitorRepository.getOne(1L).getUsername();
		String sec = esService.getOne(1L).getSector().getSectorMark();
		double p = reservationRepository.getOne(1L).getPrice();
		boolean value = ticketService.reservation(tickets, v, p);
		
		assertEquals("korisnik", v);
		assertNotEquals("SEC", sec );
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void testReservationNotCreatedEvSectWithSecNotExist() throws ObjectNotFoundException, InvalidInputException{
		Reservation r = reservationRepository.getOne(1L);
		TicketDto t=new  TicketDto(1L,"SEC444", 1, 1);
		List<TicketDto> tickets=new ArrayList<>();
		tickets.add(t);	
		String v = visitorRepository.getOne(1L).getUsername();
		Long es = esService.getOne(1L).getId();
		double p = reservationRepository.getOne(1L).getPrice();
		boolean value = ticketService.reservation(tickets, v, p);
		
		assertEquals("korisnik", v);
		assertNotEquals((Long)2L, es);
		
	}
	
	
	@Test
	public void testReservationCreated() throws ObjectNotFoundException, InvalidInputException{
		//Reservation r = reservationRepository.getOne(1L);
		TicketDto t=new  TicketDto(2L,"SEC344", 2, 2);
		List<TicketDto> tickets=new ArrayList<>();
		tickets.add(t);	
		String v = visitorRepository.getOne(1L).getUsername();
		double p = reservationRepository.getOne(1L).getPrice();
		boolean value = ticketService.reservation(tickets, v, p);
		
		assertTrue(value);
		
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void testReservationNotCreatedUserNotExist() throws ObjectNotFoundException, InvalidInputException{
		Reservation r = reservationRepository.getOne(1L);
		TicketDto t=new  TicketDto(1L,"SEC444", 1, 1);
		List<TicketDto> tickets=new ArrayList<>();
		tickets.add(t);	
		String v = visitorRepository.getOne(1L).getUsername();
		double p = reservationRepository.getOne(1L).getPrice();
		boolean value = ticketService.reservation(tickets, v, p);
		
		assertEquals("user", v);
	}
	
	@Test()
	public void testTicketBought() throws ObjectNotFoundException, InvalidInputException{
		
		boolean value = ticketService.buy(1L);
		
		assertTrue(value);
	}
	
	@Test()
	public void testCanceledReservation() throws ObjectNotFoundException, InvalidInputException{
		
		boolean value = ticketService.cancelReservation(1L);
		
		assertTrue(value);
	}
}
