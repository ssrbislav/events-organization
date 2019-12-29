package com.ftn.eventsorganization.repository;

import com.ftn.eventsorganization.model.Event;
import com.ftn.eventsorganization.model.EventSector;
import com.ftn.eventsorganization.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventSectorRepository extends JpaRepository<EventSector, Long> {

    Optional<EventSector> findByEventIdAndSectorId(Long eventId, Long sectorId);

    List<EventSector> findAllByEvent(Event event);

    List<EventSector> findAllBySector(Sector sector);

    Optional<EventSector> findById(Long id);
}
