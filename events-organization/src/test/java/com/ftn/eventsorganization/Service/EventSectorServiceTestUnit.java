package com.ftn.eventsorganization.Service;

import com.ftn.eventsorganization.DTO.EventSectorDTO;
import com.ftn.eventsorganization.enumeration.SectorType;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.*;
import com.ftn.eventsorganization.repository.*;
import com.ftn.eventsorganization.service.IEventSectorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class EventSectorServiceTestUnit {

    @MockBean
    EventSectorRepository repository;

    @Autowired
    IEventSectorService service;

    @MockBean
    LocationRepository locationRepository;

    @MockBean
    HallRepository hallRepository;

    @MockBean
    SectorRepository sectorRepository;

    @MockBean
    EventRepository eventRepository;

    @Before
    public void setUp() {
        Location location = new Location(1L, "Lokacija", "Gogoljeva", 30, "Novi Sad", "21000", "Srbija");
        Location location2 = new Location(2L, "Lokacija2", "Bulevar", 40, "Beograd", "11000", "Srbija");
        Mockito.when(locationRepository.findByIdAndDeletedIsFalse(1L)).thenReturn(Optional.of(location));
        Mockito.when(locationRepository.findByIdAndDeletedIsFalse(2L)).thenReturn(Optional.of(location2));

        Hall hall  = new Hall("Master sajam", locationRepository.findByIdAndDeletedIsFalse(1L).get());
        hall.setId(1L);
        Hall hall2  = new Hall("Master sajam", locationRepository.findByIdAndDeletedIsFalse(1L).get());
        hall2.setId(2L);
        Hall hall3  = new Hall("Beogradska arena", locationRepository.findByIdAndDeletedIsFalse(2L).get());
        hall3.setId(3L);
        Mockito.when(hallRepository.findByIdAndDeletedIsFalse(1L)).thenReturn(Optional.of(hall));
        Mockito.when(hallRepository.findByIdAndDeletedIsFalse(2L)).thenReturn(Optional.of(hall2));
        Mockito.when(hallRepository.findByIdAndDeletedIsFalse(3L)).thenReturn(Optional.of(hall3));


        Event event = new Event();
        event.setId(1L);
        Event event2 = new Event();
        event2.setId(2L);
        Event event3 = new Event();
        event3.setId(3L);

        Mockito.when(eventRepository.findByIdAndDeletedIsFalse(1L)).thenReturn(Optional.of(event));
        Mockito.when(eventRepository.findByIdAndDeletedIsFalse(2L)).thenReturn(Optional.of(event2));
        Mockito.when(eventRepository.findByIdAndDeletedIsFalse(3L)).thenReturn(Optional.of(event3));

        Sector sector = new Sector();
        sector.setId(1L);
        Sector sector2 = new Sector();
        sector.setId(2L);
        Sector sector3 = new Sector();
        sector.setId(3L);

        Mockito.when(sectorRepository.findByIdAndDeletedIsFalse(1L)).thenReturn(Optional.of(sector));
        Mockito.when(sectorRepository.findByIdAndDeletedIsFalse(2L)).thenReturn(Optional.of(sector2));
        Mockito.when(sectorRepository.findByIdAndDeletedIsFalse(3L)).thenReturn(Optional.of(sector3));

        EventSector eventSector = new EventSector(event, sector, 50.0, SectorType.REGULAR);
        eventSector.setId(1L);
        EventSector eventSector2 = new EventSector(event, sector2, 550.0, SectorType.VIP);
        eventSector2.setId(2L);
        EventSector eventSector3 = new EventSector(event2, sector3, 60.0, SectorType.LOVE);
        eventSector3.setId(3L);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(eventSector));
        Mockito.when(repository.findById(2L)).thenReturn(Optional.of(eventSector2));
        Mockito.when(repository.findById(3L)).thenReturn(Optional.of(eventSector3));

        List<EventSector> eventSectors = new ArrayList<>();
        eventSectors.add(eventSector);
        eventSectors.add(eventSector2);
        eventSectors.add(eventSector3);

        Mockito.when(repository.findAll()).thenReturn(eventSectors);

        List<EventSector> eventSectors2 = new ArrayList<>();
        eventSectors2.add(eventSector);
        eventSectors2.add(eventSector2);

        Mockito.when(repository.findAllByEvent(event)).thenReturn(eventSectors2);

        List<EventSector> eventSectors3 = new ArrayList<>();
        eventSectors3.add(eventSector3);
        Mockito.when(repository.findAllBySector(sector)).thenReturn(eventSectors3);
    }

    @Test
    public void testFindAll() {
        List<EventSector> eventSectors = service.findAll();

        assertNotNull(eventSectors);
        assertThat(eventSectors).hasSize(3);
    }

    @Test
    public void testGetOne() throws ObjectNotFoundException {
        EventSector eventSector = service.getOne(1L);

        assertNotNull(eventSector);
        assertEquals((Long)3L, eventSector.getSector().getId());
        assertEquals((Long)1L, eventSector.getEvent().getId());
        assertEquals(SectorType.REGULAR, eventSector.getSectorType());
    }

    @Test
    public void testAllBySector() throws ObjectNotFoundException {
        List<EventSector> eventSectors = service.findAllBySector(1L);

        assertNotNull(eventSectors);
        assertThat(eventSectors).hasSize(1);
    }

    @Test
    public void testAllByEvent() throws ObjectNotFoundException {
        List<EventSector> eventSectors = service.findAllByEvent(1L);

        assertNotNull(eventSectors);
        assertThat(eventSectors).hasSize(2);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testEventNotExist() throws InvalidInputException, ObjectNotFoundException {
        EventSectorDTO dto = new EventSectorDTO(1L, 5L, 150.0, SectorType.REGULAR);
        service.create(dto);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testSectorNotExist() throws InvalidInputException, ObjectNotFoundException {
        EventSectorDTO dto = new EventSectorDTO(5L, 1L, 50.0, SectorType.VIP);
        service.create(dto);
    }
}
