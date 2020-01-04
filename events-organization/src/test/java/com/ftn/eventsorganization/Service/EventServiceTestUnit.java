package com.ftn.eventsorganization.Service;

import com.ftn.eventsorganization.DTO.EventDTO;
import com.ftn.eventsorganization.enumeration.EventType;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Event;
import com.ftn.eventsorganization.model.Location;
import com.ftn.eventsorganization.repository.EventRepository;
import com.ftn.eventsorganization.repository.LocationRepository;
import com.ftn.eventsorganization.service.IEventService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class EventServiceTestUnit {

    @MockBean
    EventRepository eventRepository;

    @Autowired
    IEventService eventService;

    @MockBean
    LocationRepository locationRepository;

    @Before
    public void setUp() throws ParseException {
        Location location = new Location(1L, "Arena", "Bulevar", 30, "Beograd", "11000", "Srbija");
        Mockito.when(locationRepository.findByIdAndDeletedIsFalse(1L)).thenReturn(Optional.of(location));

        Location location2 = new Location(2L, "Sajam", "Nobosadskog sajma", 30, "Novi Sad", "21000", "Srbija");
        Mockito.when(locationRepository.findByIdAndDeletedIsFalse(2L)).thenReturn(Optional.of(location2));

        String valuee = "2020-01-01";
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(valuee);
        String value = "2020-01-09";
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(value);

        Event event = new Event("Koncert", startDate, endDate, EventType.CONCERT, location);
        event.setId(1L);

        Event event2 = new Event("Rakijada", startDate, endDate, EventType.FESTIVAL, location2);
        event2.setId(2L);
        Event event3 = new Event("Kobasicijada", startDate, endDate, EventType.FAIR, location2);
        event3.setId(3L);

        Event eventDelete = new Event("Event", startDate, endDate, EventType.FAIR, location2);
        eventDelete.setId(5L);

        Mockito.when(eventRepository.findByIdAndDeletedIsFalse(5L)).thenReturn(Optional.of(eventDelete));

        Mockito.when(eventRepository.findByIdAndDeletedIsFalse(1L)).thenReturn(Optional.of(event));
        Mockito.when(eventRepository.findByIdAndDeletedIsFalse(2L)).thenReturn(Optional.of(event2));
        Mockito.when(eventRepository.findByIdAndDeletedIsFalse(3L)).thenReturn(Optional.of(event3));

        Mockito.when(eventRepository.save(event)).thenReturn(event);

        Mockito.when(eventRepository.save(event3)).thenReturn(event3);

        Mockito.when(eventRepository.findByIdAndDeletedIsFalse(9L)).thenReturn(null);

        List<Event> events = new ArrayList<>();
        events.add(event);
        events.add(event2);
        events.add(event3);
        Mockito.when(eventRepository.findAllByDeletedIsFalse()).thenReturn(events);
    }

    @Test
    public void testFindAll() {
        List<Event> events = eventService.findAll();

        assertNotNull(events);
        assertThat(events).hasSize(3);
    }

    @Test(expected = NullPointerException.class)
    public void testNull() throws ObjectNotFoundException {
        Event event = eventService.getOne(9L);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testObjectNotFound() throws ObjectNotFoundException {
        Event event = eventService.getOne(8L);
    }

    @Test(expected = InvalidInputException.class)
    public void testWrongStartDate() throws ParseException, InvalidInputException, ObjectNotFoundException {
        String valuee = "2020-10-01";
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(valuee);
        String value = "2020-01-09";
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(value);

        EventDTO dto = new EventDTO("Ime", startDate, endDate, EventType.FAIR, 1L);
        eventService.create(dto);
    }

    @Test(expected = InvalidInputException.class)
    public void testInvalidName() throws ParseException, InvalidInputException, ObjectNotFoundException {
        String valuee = "2020-01-01";
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(valuee);
        String value = "2020-01-09";
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(value);

        EventDTO dto = new EventDTO("", startDate, endDate, EventType.FAIR, 1L);
        eventService.create(dto);
    }

    @Test
    public void testGetOne() throws ObjectNotFoundException {
        Event event = eventService.getOne(1L);

        assertNotNull(event);
        assertEquals((Long) 1l, event.getId());
        assertEquals("Koncert", event.getName());
        assertEquals((Long) 1L, event.getLocation().getId());
        assertEquals(EventType.CONCERT, event.getEventType());
    }

    @Test
    public void testUpdate() throws ObjectNotFoundException {
        Event event = eventService.getOne(3L);
        event.setName("Dogadjaj");
        Event updateEvent = eventService.update(event);

        assertNotNull(updateEvent);
        assertEquals("Dogadjaj", updateEvent.getName());
        assertEquals((Long) 3L, updateEvent.getId());
    }

    @Test
    public void testDetele() throws ObjectNotFoundException {
        Event event = eventService.getOne(5L);
        boolean deleted = eventService.delete(event.getId());

        assertThat(deleted).isTrue();
    }

}
