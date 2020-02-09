package com.ftn.eventsorganization.repository;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.constraints.AssertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.eventsorganization.enumeration.EventType;
import com.ftn.eventsorganization.enumeration.RoleType;
import com.ftn.eventsorganization.enumeration.SectorType;
import com.ftn.eventsorganization.model.Event;
import com.ftn.eventsorganization.model.EventSector;
import com.ftn.eventsorganization.model.Hall;
import com.ftn.eventsorganization.model.Location;
import com.ftn.eventsorganization.model.Reservation;
import com.ftn.eventsorganization.model.Sector;
import com.ftn.eventsorganization.model.Ticket;
import com.ftn.eventsorganization.model.User;
import com.ftn.eventsorganization.model.Visitor;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@Rollback(value = true)
public class TicketRepositoryTest {
	
	@Autowired
	private TicketRepository repository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	HallRepository hallRepository;
	
	@Autowired 
	private EventSectorRepository esRepository;

	
		
	@Test
	public void testFindAllByExpireDAte(){			
		LocalDate expireDate = new java.sql.Date(Calendar.getInstance().getTime().getTime()).toLocalDate();			
		java.sql.Date d = java.sql.Date.valueOf(expireDate);
		List <Reservation> found = this.reservationRepository.findAllByExpireDate(d);	
		assertNotNull(found);
		
	}
	
	
	@Test
	public void testFindByEventSectorAndReserved() throws ParseException{
		List<Ticket> tickets = (List<Ticket>)this.repository.findByEventSectorAndReserved(esRepository.getOne(4L), true);
		assertTrue(tickets.size() == 0);
	}
	
	@Test
	public void testFindByBoughtAndReservation(){
		List<Ticket> t = (List<Ticket>)this.repository.findByBoughtAndReservation(false, reservationRepository.getOne(1L));
		assertTrue(t.size() == 1);
	} 
	
	@Test
	public void testFindByReservation(){
		List<Ticket> ticket = (List<Ticket>)this.repository.findByReservation(reservationRepository.getOne(1L));
		assertTrue(ticket.size() == 1);
	}
	
}
