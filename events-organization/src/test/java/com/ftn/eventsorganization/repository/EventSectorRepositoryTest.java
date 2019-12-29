package com.ftn.eventsorganization.repository;

import com.ftn.eventsorganization.enumeration.EventType;
import com.ftn.eventsorganization.enumeration.SectorType;
import com.ftn.eventsorganization.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@Rollback(value = true)
public class EventSectorRepositoryTest {

    @Autowired
    EventSectorRepository eventSectorRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    HallRepository hallRepository;

    @Autowired
    SectorRepository sectorRepository;

    @Autowired
    EventRepository eventRepository;

    @Before
    public void setUp() {
        Location location = new Location();
        location.setName("Sajam");
        location.setStreetName("Novosadskog sajma");
        location.setNumber(30);
        location.setCountry("Srbija");
        location.setZipCode("21000");
        Location loc = locationRepository.save(location);

        Hall hall = new Hall();
        hall.setName("Arena");
        hall.setLocation(loc);
        Hall savedHall = hallRepository.save(hall);

        Sector sector = new Sector();
        sector.setSectorMark("Sector 3");
        sector.setNumOfColumns(4L);
        sector.setNumOfRows(4L);
        sector.setHall(savedHall);
        Sector savedSector = sectorRepository.save(sector);

        Event event = new Event();
        event.setLocation(loc);
        event.setStartDate(new Date());
        event.setEndDate(new Date());
        event.setName("Koncert");
        event.setEventType(EventType.CONCERT);
        Event savedEvent = eventRepository.save(event);
    }

    @Test
    @Transactional
    @Rollback
    public void testSaveEventSector() {
        EventSector eventSector = new EventSector();
        eventSector.setSectorType(SectorType.REGULAR);
        eventSector.setSector(sectorRepository.getOne(1L));
        eventSector.setEvent(eventRepository.getOne(1L));
        eventSector.setPrice(110.0);
        EventSector es = eventSectorRepository.save(eventSector);

        assertNotNull(es);
        assertEquals(eventSector.getId(), es.getId());
    }

    @Test(expected = IncorrectResultSizeDataAccessException.class)
    @Transactional
    @Rollback
    public void testFindByEventIdAndSectorI() {
        Long eventId = eventRepository.findById(1L).get().getId();
        Long sectorId = sectorRepository.findById(1L).get().getId();

        Optional<EventSector> eventSector = eventSectorRepository.findByEventIdAndSectorId(eventId, sectorId);

        assertEquals(eventId, eventSector.get().getEvent().getId());
        assertEquals(sectorId, eventSector.get().getSector().getId());
    }

    @Test
    @Transactional
    @Rollback
    public void testFindAllBySector() {
        EventSector eventSector = new EventSector();
        eventSector.setPrice(200);
        eventSector.setEvent(eventRepository.getOne(1L));
        Sector sector = sectorRepository.getOne(1L);
        eventSector.setSector(sector);
        eventSectorRepository.save(eventSector);

        List<EventSector> eventSectors = eventSectorRepository.findAllBySector(sector);

        assertFalse(eventSectors.isEmpty());
    }

    @Test
    @Transactional
    @Rollback
    public void testFindAllByEvent() {
        EventSector eventSector = new EventSector();
        eventSector.setPrice(200);
        eventSector.setSector(sectorRepository.getOne(1L));
        Event event = eventRepository.getOne(1L);
        eventSector.setEvent(event);
        eventSectorRepository.save(eventSector);

        List<EventSector> eventSectors = eventSectorRepository.findAllByEvent(event);

        assertFalse(eventSectors.isEmpty());
    }
}
