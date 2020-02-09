package com.ftn.eventsorganization.Service;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.eventsorganization.DTO.TicketDto;
import com.ftn.eventsorganization.enumeration.EventType;
import com.ftn.eventsorganization.enumeration.RoleType;
import com.ftn.eventsorganization.enumeration.SectorType;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Event;
import com.ftn.eventsorganization.model.EventSector;
import com.ftn.eventsorganization.model.Hall;
import com.ftn.eventsorganization.model.Location;
import com.ftn.eventsorganization.model.Reservation;
import com.ftn.eventsorganization.model.Role;
import com.ftn.eventsorganization.model.Sector;
import com.ftn.eventsorganization.model.Ticket;
import com.ftn.eventsorganization.model.User;
import com.ftn.eventsorganization.model.UserPrinciple;
import com.ftn.eventsorganization.model.Visitor;
import com.ftn.eventsorganization.repository.EventRepository;
import com.ftn.eventsorganization.repository.EventSectorRepository;
import com.ftn.eventsorganization.repository.HallRepository;
import com.ftn.eventsorganization.repository.LocationRepository;
import com.ftn.eventsorganization.repository.ReservationRepository;
import com.ftn.eventsorganization.repository.SectorRepository;
import com.ftn.eventsorganization.repository.TicketRepository;
import com.ftn.eventsorganization.repository.VisitorRepository;
import com.ftn.eventsorganization.security.JwtAuthTokenFilter;
import com.ftn.eventsorganization.service.TicketService;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class TicketServiceTestUnit {

	@Autowired
	private TicketService service;
	
	@MockBean
	private TicketRepository ticketRepository;
	
	@MockBean
	private ReservationRepository reservationRepository;
	
	@MockBean
	private VisitorRepository visitorRepository;

	@MockBean
	private EventRepository eventRepository;

	@MockBean
	private SectorRepository sectorRepository;
	
	@MockBean 
	private EventSectorRepository esRepository;
	
    private static final Logger logger = LoggerFactory.getLogger(TicketServiceTestUnit.class);
	
	@Before
	public void setUp() throws ParseException{
		Location location = new Location(1L, "Arena", "Bulevar", 30, "Beograd", "11000", "Srbija");
        
		Hall hall = new Hall("Hala1", location);
        
		Sector sector = new Sector("SEC", 4L, 4L, hall);
		sector.setId(1L);
		Mockito.when(sectorRepository.findBySectorMark("SEC")).thenReturn(Optional.of(sector));
        
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date   date       = format.parse ( "2020-02-02" );  
        Date   date2       = format.parse ( "2020-03-03" );  
        
        Event event = new Event("Predstava", date, date2, EventType.FESTIVAL, location);
        event.setId(1L);
        Mockito.when(eventRepository.findById(1L)).thenReturn(Optional.of(event));
        
        EventSector es = new EventSector(event, sector, 100, SectorType.REGULAR);
        Mockito.when(esRepository.findByEventIdAndSectorId(1L, 1L)).thenReturn(Optional.of(es));
       
        Set <Role> r = new HashSet<>();
        r.add(new Role(RoleType.ROLE_VISITOR));
        
        Visitor v = new Visitor("user", "1234", "user@user.com", "Pera", "Peric",
                date2, "Adresa", "062016468",r , true, false);
        
        Mockito.when(visitorRepository.findByUsername("user")).thenReturn(Optional.of(v));
        
        List<Ticket> ticketsEmp = new ArrayList<>();
        List<Ticket> ticket2 = new ArrayList<>();
        java.sql.Date resv = java.sql.Date.valueOf("2020-02-05");
        java.sql.Date exp = java.sql.Date.valueOf("2020-02-07");
        
        java.sql.Date resv2 = java.sql.Date.valueOf("2020-02-05");
        java.sql.Date exp2 = java.sql.Date.valueOf("2020-02-10");
        
        Reservation res = new Reservation(1L, v, ticketsEmp, false, resv, exp , false);
        Reservation res2 = new Reservation(2L, v, ticketsEmp, false, resv2, exp2 , false);
        
        Ticket t1 = new Ticket(1L, res, es, true, false, v, 4, 4);
        Ticket t2 = new Ticket(2L, res2, es, true, true, v, 3, 3);
        Ticket t3 = new Ticket(3L, res, es, true, false, v, 1, 1);
        ticketsEmp.add(t1);
        ticketsEmp.add(t3);
        res.setTickets(ticketsEmp);
        ticket2.add(t2);
        res2.setTickets(ticket2);
        
        List<Ticket> ticketR = Arrays.asList(t1,t3);

        
        Mockito.when(ticketRepository.findByBoughtAndReservation(false, res)).thenReturn(ticketR);//istekla reservacija
        
        Mockito.when(ticketRepository.findByEventSectorAndReserved(es, true)).thenReturn(ticketsEmp);//da li postoji
        
        Mockito.when(ticketRepository.findByReservation(res2)).thenReturn(ticket2); //otkazivanje
        
 
	}
	
	@Test(expected=ObjectNotFoundException.class)
	public void testReservationSectorNotFound() throws ParseException, ObjectNotFoundException, InvalidInputException{
		
		TicketDto t=new  TicketDto(1L,"SE", 1, 1);
		List<TicketDto> tickets=new ArrayList<>();
		tickets.add(t);
		
		boolean value=service.reservation(tickets, "user", 100);
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testReservationInvalidInput() throws ParseException, ObjectNotFoundException, InvalidInputException{
		
		TicketDto t=new  TicketDto(1L,"SEC", 1, 1);
		List<TicketDto> tickets=new ArrayList<>();
		tickets.add(t);
		
		boolean value=service.reservation(tickets, "user", 100);
	}
	
	@Test(expected=InvalidInputException.class)
	public void testReservationMoreThanFiveCards() throws ParseException, ObjectNotFoundException, InvalidInputException{
		
		TicketDto t=new  TicketDto(1L,"SEC", 1, 1);
		List<TicketDto> tickets=new ArrayList<>();
		tickets.add(t);
		tickets.add(t);
		tickets.add(t);
		tickets.add(t);
		tickets.add(t);
		tickets.add(t);	
		boolean value=service.reservation(tickets, "user", 100);
	}

	@Test
	public void testReservationSuccessful() throws ParseException, ObjectNotFoundException, InvalidInputException{
		
		Location location = new Location(1L, "Arena", "Bulevar", 30, "Beograd", "11000", "Srbija");
		Hall hall = new Hall("Hala1", location);
		Sector sector = new Sector("SEC", 4L, 4L, hall);
		sector.setId(1L);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date   date       = format.parse ( "2020-02-02" );  
        Date   date2       = format.parse ( "2020-03-03" );  
        Event event = new Event("Predstava", date, date2, EventType.FESTIVAL, location);
        event.setId(1L); 
        EventSector es = new EventSector(event, sector, 100, SectorType.REGULAR);
		
        boolean value = service.ticketExist(es, 4, 3);
	}
	
	@Test
	public void testReservationUnsuccessful() throws ParseException, ObjectNotFoundException, InvalidInputException{
		
		Location location = new Location(1L, "Arena", "Bulevar", 30, "Beograd", "11000", "Srbija");
		Hall hall = new Hall("Hala1", location);
		Sector sector = new Sector("SEC", 4L, 4L, hall);
		sector.setId(1L);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date   date       = format.parse ( "2020-02-02" );  
        Date   date2       = format.parse ( "2020-03-03" );  
        Event event = new Event("Predstava", date, date2, EventType.FESTIVAL, location);
        event.setId(1L); 
        EventSector es = new EventSector(event, sector, 100, SectorType.REGULAR);
        
        boolean value = service.ticketExist(es, 4, 4);
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void testBuyUnSuccessful() throws ParseException, ObjectNotFoundException{
		Location location = new Location(1L, "Arena", "Bulevar", 30, "Beograd", "11000", "Srbija");
		Hall hall = new Hall("Hala1", location);
    	Sector sector = new Sector("SEC", 4L, 4L, hall);
		sector.setId(1L);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date   date       = format.parse ( "2020-02-02" );  
        Date   date2       = format.parse ( "2020-03-03" );  
        Event event = new Event("Predstava", date, date2, EventType.FESTIVAL, location);
        event.setId(1L);
        EventSector es = new EventSector(event, sector, 100, SectorType.REGULAR);
        Set <Role> r = new HashSet<>();
        r.add(new Role(RoleType.ROLE_VISITOR));
        Visitor v = new Visitor("user", "1234", "user@user.com", "Pera", "Peric",
                date2, "Adresa", "062016468",r , true, false);
        List<Ticket> ticketsEmp = new ArrayList<>();
        java.sql.Date resv = java.sql.Date.valueOf("2020-02-05");
        java.sql.Date exp = java.sql.Date.valueOf("2020-02-07");
        
        Reservation res = new Reservation(1L, v, ticketsEmp, false, resv, exp , false);
        res.setId(1L);
        Ticket t1 = new Ticket(1L, res, es, true, false, v, 4, 4);
        ticketsEmp.add(t1);
        res.setTickets(ticketsEmp);

        boolean value = service.buy(3L);
	}
	
	
	
	@Test(expected = ObjectNotFoundException.class)
	public void testCancenlReservationUnSuccessful() throws ParseException, ObjectNotFoundException{
		Location location = new Location(1L, "Arena", "Bulevar", 30, "Beograd", "11000", "Srbija");
		Hall hall = new Hall("Hala1", location);
    	Sector sector = new Sector("SEC", 4L, 4L, hall);
		sector.setId(1L);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date   date       = format.parse ( "2020-02-02" );  
        Date   date2       = format.parse ( "2020-03-03" );  
        Event event = new Event("Predstava", date, date2, EventType.FESTIVAL, location);
        event.setId(1L);
        EventSector es = new EventSector(event, sector, 100, SectorType.REGULAR);
        Set <Role> r = new HashSet<>();
        r.add(new Role(RoleType.ROLE_VISITOR));
        Visitor v = new Visitor("user", "1234", "user@user.com", "Pera", "Peric",
                date2, "Adresa", "062016468",r , true, false);
        List<Ticket> ticketsEmp = new ArrayList<>();
        java.sql.Date resv = java.sql.Date.valueOf("2020-02-05");
        java.sql.Date exp = java.sql.Date.valueOf("2020-02-07");
        
        Reservation res = new Reservation(1L, v, ticketsEmp, false, resv, exp , false);
        res.setId(1L);
        Ticket t1 = new Ticket(1L, res, es, true, false, v, 4, 4);
        ticketsEmp.add(t1);
        res.setTickets(ticketsEmp);

        boolean value = service.buy(3L);
	}
}
