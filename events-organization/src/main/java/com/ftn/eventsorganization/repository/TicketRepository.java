package com.ftn.eventsorganization.repository;

import com.ftn.eventsorganization.model.EventSector;
import com.ftn.eventsorganization.model.Reservation;
import com.ftn.eventsorganization.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Set;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllById(Long id);
    List <Ticket> findByEventSectorAndReserved(EventSector id,boolean rez);
    List<Ticket> findByBoughtAndReservation(boolean b,Reservation r);
    List<Ticket> findByReservation(Reservation r);
}
