package com.ftn.eventsorganization.repository;

import com.ftn.eventsorganization.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllById(Long id);
}
