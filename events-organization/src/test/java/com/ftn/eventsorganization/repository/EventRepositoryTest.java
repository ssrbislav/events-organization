package com.ftn.eventsorganization.repository;

import com.ftn.eventsorganization.enumeration.EventType;
import com.ftn.eventsorganization.model.Event;
import com.ftn.eventsorganization.model.Location;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@Rollback(value = true)
public class EventRepositoryTest {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    LocationRepository locationRepository;

    @Before
    public void setUp() {
        Location location = new Location();
        location.setName("Sajam");
        location.setStreetName("Novosadskog sajma");
        location.setNumber(30);
        location.setCountry("Srbija");
        location.setZipCode("21000");
        Location loc = locationRepository.save(location);
    }

    @Test
    @Transactional
    @Rollback
    public void testSaveEvent() {
        Event event = new Event();
        event.setLocation(locationRepository.findById(1L).get());
        event.setStartDate(new Date());
        event.setEndDate(new Date());
        event.setName("Koncert");
        event.setEventType(EventType.CONCERT);
        Event savedEvent = eventRepository.save(event);

        assertEquals(event.getName(), savedEvent.getName());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testSaveEmpty() {
        eventRepository.save(new Event());
    }

    @Test
    @Transactional
    @Rollback
    public void testFindAllByDeletedIsFalse() {
        Event event = new Event();
        event.setName("Event");
        event.setLocation(locationRepository.getOne(1L));
        eventRepository.save(event);

        List<Event> events = eventRepository.findAllByDeletedIsFalse();

        assertFalse(events.isEmpty());
        assertNotNull(events);
    }

    @Test
    @Transactional
    @Rollback
    public void testFindByIdAndDeletedIsFalse() {
        Event event = new Event();
        event.setLocation(locationRepository.findById(1L).get());
        event.setStartDate(new Date());
        event.setEndDate(new Date());
        event.setName("Event");
        event.setEventType(EventType.CONCERT);
        Event savedEvent = eventRepository.save(event);

        assertEquals("Event", savedEvent.getName());
        assertFalse(savedEvent.isDeleted());
        assertNotNull(savedEvent);
    }

}
