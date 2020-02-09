package com.ftn.eventsorganization.Service;

import com.ftn.eventsorganization.DTO.EventDTO;
import com.ftn.eventsorganization.enumeration.EventType;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Event;
import com.ftn.eventsorganization.service.IEventService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
@Rollback
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventSeviceTest {

    @Autowired
    IEventService eventService;

    @Test
    public void testFindAll() {
        List<Event> events = eventService.findAll();

        assertFalse(events.isEmpty());
        assertThat(events).hasSize(4);
    }

    @Test
    public void testGetOne() throws ObjectNotFoundException {
        Event event = eventService.getOne(1L);

        assertFalse(event.isDeleted());
        assertEquals("VIP Fest", event.getName());
        assertEquals(EventType.FESTIVAL, event.getEventType());
        assertEquals((Long) 2L, event.getLocation().getId());
    }

    @Test(expected = InvalidInputException.class)
    public void testBadDate() throws ParseException, InvalidInputException, ObjectNotFoundException {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setName("BAD Fest");
        eventDTO.setEventType(EventType.CONCERT);
        eventDTO.setLocationId(1L);

        String valuee = "2020-01-01";
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(valuee);
        String value = "2020-01-09";
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(value);

        eventDTO.setStartDate(endDate);
        eventDTO.setEndDate(startDate);
        eventService.create(eventDTO);
    }

    @Test(expected = InvalidInputException.class)
    public void testBadName() throws ParseException, InvalidInputException, ObjectNotFoundException {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setName("");
        eventDTO.setEventType(EventType.CONCERT);
        eventDTO.setLocationId(1L);

        String valuee = "2020-01-01";
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(valuee);
        String value = "2020-01-09";
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(value);

        eventDTO.setStartDate(endDate);
        eventDTO.setEndDate(startDate);
        eventService.create(eventDTO);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testLocationNotPresent() throws ParseException, InvalidInputException, ObjectNotFoundException {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setName("Location Fest");
        eventDTO.setEventType(EventType.CONCERT);
        eventDTO.setLocationId(13L);

        String valuee = "2020-01-01";
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(valuee);
        String value = "2020-01-09";
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(value);

        eventDTO.setStartDate(startDate);
        eventDTO.setEndDate(endDate);
        eventService.create(eventDTO);
    }

    @Test()
    public void testCreateSuccessfully() throws InvalidInputException, ObjectNotFoundException, ParseException {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setName("OK Fest");
        eventDTO.setEventType(EventType.CONCERT);
        eventDTO.setLocationId(1L);

        String valuee = "2020-03-03";
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(valuee);
        String value = "2020-03-09";
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(value);

        eventDTO.setStartDate(startDate);
        eventDTO.setEndDate(endDate);
        Event event = eventService.create(eventDTO);

        assertNotNull(event);
        assertEquals("OK Fest", event.getName());
        assertEquals((Long) 1L, event.getLocation().getId());
        assertFalse(event.isDeleted());
    }

    @Test
    public void updateEvent() throws ObjectNotFoundException, InvalidInputException {
        Event event = eventService.getOne(1L);
        event.setEventType(EventType.CONCERT);
        event.setName("VIP Concert");
        Event eventUpdate = eventService.update(event);

        assertEquals(EventType.CONCERT, eventUpdate.getEventType());
        assertEquals("VIP Concert", eventUpdate.getName());
    }

    @Test
    public void deleteTest() throws ObjectNotFoundException {
        Event event = eventService.getOne(1L);
        boolean deleted = eventService.delete(event.getId());

        assertTrue(deleted);
    }

}
