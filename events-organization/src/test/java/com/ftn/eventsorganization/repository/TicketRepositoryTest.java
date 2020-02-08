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
	private VisitorRepository visitorRepository;

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private SectorRepository sectorRepository;
	
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
	
	/*
	@Test
	public void testFindByEventSectorAndReserved() throws ParseException{
		Location l = new Location("Sajam","Kralja Petra", 30, "Novi Sad", "21000", "Srbija");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateEnd = "2020-05-08";
		Date date = sdf.parse(dateEnd);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String date2 = "2020-02-05";
		Date dateStart = sdf2.parse(date2);
		EventSector es = new EventSector(new Event("Sajam poljoprivrede", dateStart, date, EventType.FAIR, l), 
				new Sector( "SEC444", 4L, 4L, new Hall("Master centar", l)), 300, SectorType.REGULAR);
		
		List<Ticket> tickets = this.repository.findByEventSectorAndReserved(es, true);
		assertNotNull(tickets);
	}*/
}
