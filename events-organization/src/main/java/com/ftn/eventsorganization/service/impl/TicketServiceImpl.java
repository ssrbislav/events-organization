package com.ftn.eventsorganization.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.eventsorganization.DTO.TicketDto;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Event;
import com.ftn.eventsorganization.model.EventSector;
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
import com.ftn.eventsorganization.service.TicketService;



@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	private TicketRepository repository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private VisitorRepository visitorRepository;

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private SectorRepository sectorRepository;
	
	@Autowired 
	private EventSectorRepository esRepository;
	
	
	@Override
	@Transactional(rollbackFor = ObjectNotFoundException.class)
	//kreiranje rezervacije
	public boolean reservation(List<TicketDto> tickets, String username) throws ObjectNotFoundException, InvalidInputException {
		Visitor v = this.visitorRepository.findByUsername(username).orElseThrow(() ->  new ObjectNotFoundException("User with username " + username + " does not exist!"));
		
		if(tickets.size() > 5){
			throw new InvalidInputException("You can not reserve more than 5 tickets!" );
		}
		Reservation r =  new Reservation();
		r.setCanceled(false);
		
		r.setReservationDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		//dogadjaj za koji se kupuju karte, kod svih karata mora biti isti
		Event e = eventRepository.findById(tickets.get(0).getEventId()).orElseThrow(() -> new ObjectNotFoundException("Event with id: " + tickets.get(0).getEventId() + " does not exist." ));
		r.setExpireDate(calculateExpireDate(e.getStartDate())); // postavljanje datuma isteka rezervacije
		r.setVisitor(v);
		boolean savedticket = true;
		for(TicketDto t : tickets){
			//ovde napravimo svaku kartu i dodajemo je u bazu 
			Sector s  = sectorRepository.findBySectorMark(t.getSectorMark()).orElseThrow(() -> new ObjectNotFoundException("Sector with mark: " + t.getSectorMark() + " does not exist"));
			if((t.getRow()<1 || t.getRow() > s.getNumOfRows()) || (t.getColumn() <1 || s.getNumOfColumns() < t.getColumn())) {
				continue; //nece upisati kartu sa negativnim brojem sedista i vecim brojem od opsega sedista
			}
			EventSector es = esRepository.findByEventIdAndSectorId(e.getId(),s.getId()).orElseThrow(()-> new ObjectNotFoundException("Event sector does not exist"));
			boolean ticketEx = ticketExist(es, t.getColumn(), t.getRow());
			if (ticketEx == true){
				savedticket = false;
				continue; //ukoliko karta vec postoji
			}
			Ticket ticket =  new Ticket(r, es, true, false, v, t.getColumn(), t.getRow());
			Ticket tck= repository.save(ticket);
			if (tck == null){
				savedticket = false;
				
			}
		}
		if (savedticket == true){
			reservationRepository.save(r);}
		else{
			throw new InvalidInputException("Unesena rezervacija je neadekvatna!");
		}
		return true;
	}

	//racunanje datuma isteka rezervacije
	public Date calculateExpireDate(java.util.Date startEventDate) throws InvalidInputException { 
		
		LocalDate eventDate  = startEventDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();//pocetak dogadjaja		
		
		LocalDate expireDate = new java.sql.Date(Calendar.getInstance().getTime().getTime()).toLocalDate();	
		
		if (expireDate.plusDays(5).isBefore(eventDate)){
			expireDate = expireDate.plusDays(6); //expire date 
			
		}else if (expireDate.plusDays(5).isAfter(eventDate)){
			expireDate = eventDate.minusDays(2);
		}else if (expireDate.equals(eventDate)){
			throw new InvalidInputException("On thus day, you can only buy a ticket!" );
		}
		
		return java.sql.Date.valueOf(expireDate);
	}

	@Override
	public boolean ticketExist(EventSector es, int c, int r) {
		List <Ticket> t = repository.findByEventSectorAndReserved(es, true); //lista rezervisanih karata za odredjeni dogadjaj
		
		for (Ticket ticket : t) {
			
			if(ticket.getSeat_column() == c && ticket.getSeat_row()==r){
				return true; //pronasao je vec postojecu kartu 
			}
		}
		
		return false;
	}
	
	//brisanje karata i rezervacija kojima je istekao datum
	@Scheduled(cron= "0 0 8 * * *", zone = "Europe/Paris")
	public void deleteReservation(){
	System.out.println("usao");
		LocalDate expireDate = new java.sql.Date(Calendar.getInstance().getTime().getTime()).toLocalDate();	
		expireDate = expireDate.minusDays(1);
		System.out.println(expireDate);
		Date d = java.sql.Date.valueOf(expireDate);
		List<Reservation> reservations = reservationRepository.findAllByExpireDate(d);
		
		System.out.println("velicina liste " +reservations.size());
		
		for (Reservation reservation : reservations) {
			if(findExpTickets(reservation)){
				reservationRepository.delete(reservation);
			}
		}
	}
	
	public boolean findExpTickets(Reservation r){
		List<Ticket> t = repository.findByBoughtAndReservation(false, r);
		System.out.println("velicina liste tiketa  " +t.size());
		if(t.size() == 0){
			return false;
		}
		for (Ticket ticket : t) {
			repository.delete(ticket);
		}
		return true;
	}

	//otkazivanje rezervacije
	@Override
	public boolean cancelReservation(Long id) throws ObjectNotFoundException {
		Reservation r = reservationRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Reservation with id" +id +"does not exist!"));
		List<Ticket> t = repository.findByReservation(r);
		for (Ticket ticket : t) {
			repository.delete(ticket);
		}
		reservationRepository.delete(r);
		return true;
	}

	@Override
	public boolean buy(Long id) throws ObjectNotFoundException {
		Reservation r = reservationRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Reservation with id" +id +"does not exist!"));
		List<Ticket> t = repository.findByReservation(r);
		for (Ticket ticket : t) {
			ticket.setBought(true);
			repository.save(ticket);
			}
		return true;
	}
	
	
}
