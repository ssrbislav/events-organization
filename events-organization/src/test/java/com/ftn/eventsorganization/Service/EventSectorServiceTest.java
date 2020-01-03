package com.ftn.eventsorganization.Service;

import com.ftn.eventsorganization.DTO.EventSectorDTO;
import com.ftn.eventsorganization.enumeration.EventType;
import com.ftn.eventsorganization.enumeration.SectorType;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.EventSector;
import com.ftn.eventsorganization.service.IEventSectorService;
import com.ftn.eventsorganization.service.IEventService;
import com.ftn.eventsorganization.service.ISectorService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LONG;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
@Rollback
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventSectorServiceTest {

    @Autowired
    IEventSectorService service;

    @Autowired
    IEventService eventService;

    @Autowired
    ISectorService sectorService;

    @Test
    public void testGetOne() throws ObjectNotFoundException {
        EventSector eventSector = service.getOne(1L);

        assertNotNull(eventSector);
        assertEquals((Long) 1L, eventSector.getEvent().getId());
        assertEquals((Long) 2L, eventSector.getSector().getId());
        assertEquals(SectorType.VIP, eventSector.getSectorType());
        assertEquals(200, eventSector.getPrice(), 0.1);
    }

    @Test
    public void testFindAll() {
        List<EventSector> eventSectors = service.findAll();

        assertNotNull(eventSectors);
        assertThat(eventSectors).hasSize(4);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testEventNotPresent() throws ObjectNotFoundException {
        EventSector eventSector = new EventSector(eventService.getOne(1L), sectorService.getOne(25L), -50, SectorType.VIP);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testSectorNotPresent() throws ObjectNotFoundException {
        EventSector eventSector = new EventSector(eventService.getOne(15L), sectorService.getOne(2L), -50, SectorType.VIP);
    }

    @Test(expected = InvalidInputException.class)
    public void testBadPrice() throws ObjectNotFoundException, InvalidInputException {
        EventSectorDTO dto = new EventSectorDTO(sectorService.getOne(1L).getId(),eventService.getOne(1L).getId(), -50.0, SectorType.VIP);
        EventSector eventSector = service.create(dto);
    }

    @Test
    public void testCreateSuccessfully() throws ObjectNotFoundException, InvalidInputException {
        EventSectorDTO dto = new EventSectorDTO(sectorService.getOne(1L).getId(),eventService.getOne(1L).getId(), 50.0, SectorType.VIP);
        EventSector eventSector = service.create(dto);

        assertNotNull(eventSector);
        assertEquals(SectorType.VIP, eventSector.getSectorType());
        assertEquals((Long)1L, eventSector.getEvent().getId());
        assertEquals((Long)1L, eventSector.getSector().getId());
        assertEquals(50.0, eventSector.getPrice(), 0.1);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testFindBySector() throws ObjectNotFoundException {
        List<EventSector> eventSectors = service.findAllByEvent(8L);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testFindByEvent() throws ObjectNotFoundException {
        List<EventSector> eventSector = service.findAllByEvent(7L);
    }
}
