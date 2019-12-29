package com.ftn.eventsorganization.Service;

import com.ftn.eventsorganization.DTO.LocationDTO;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Location;
import com.ftn.eventsorganization.repository.LocationRepository;
import com.ftn.eventsorganization.service.ILocationService;
import org.junit.Before;
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
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
@Rollback(value = true)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocationServiceTest {

    @Autowired
    ILocationService locationService;

    @Autowired
    LocationRepository repository;

    @Before
    public void setUp() {
        Location location = new Location("Sajmiste", "Novosadskog sajma", 30, "Novi Sad", "21000", "Srbija");
        repository.save(location);
    }

    @Test
    @Transactional
    @Rollback
    public void testFindAll() {
        List<Location> locations = locationService.findAll();

        assertNotNull(locations);
        assertFalse(locations.isEmpty());
    }

    @Test(expected = ObjectNotFoundException.class)
    @Transactional
    @Rollback
    public void testObjectNotFound() throws ObjectNotFoundException {
        locationService.getOne(50L);
    }

    @Test
    @Transactional
    @Rollback
    public void testGetOne() throws ObjectNotFoundException {
        Location location = locationService.getOne(1L);

        assertNotNull(location);
        assertEquals((Long) 1L, location.getId());
        assertEquals("Sajmiste", location.getName());
        assertEquals("Novosadskog sajma", location.getStreetName());
        assertEquals(30, location.getNumber());
        assertEquals("Novi Sad", location.getCity());
        assertEquals("21000", location.getZipCode());
        assertEquals("Srbija", location.getCountry());
        assertFalse(location.isDeleted());
    }

    @Test(expected = InvalidInputException.class)
    @Transactional
    @Rollback
    public void testSameName() throws InvalidInputException {
        LocationDTO dto = new LocationDTO("Kupaliste", "Kralja Petra",
                32, "Novi Sad", "21000", "Srbija");
        locationService.create(dto);
        locationService.create(dto);
    }

    @Test(expected = InvalidInputException.class)
    @Transactional
    @Rollback
    public void testNoName() throws InvalidInputException {
        LocationDTO dto = new LocationDTO();
        dto.setCity("Novi Sad");
        dto.setCountry("Srbija");
        dto.setNumber(30);
        dto.setStreetName("Govoljeva");
        dto.setZipCode("21000");
        locationService.create(dto);
    }

    @Test
    @Transactional
    @Rollback
    public void testCreateSuccessfull() throws InvalidInputException {
        LocationDTO dto = new LocationDTO("Lokacija", "Kralja Petra",
                32, "Novi Sad", "21000", "Srbija");
        Location location = locationService.create(dto);

        assertNotNull(location);
        assertEquals("Lokacija", location.getName());
        assertEquals("Kralja Petra", location.getStreetName());
        assertEquals(32, location.getNumber());
        assertEquals("Novi Sad", location.getCity());
        assertEquals("21000", location.getZipCode());
        assertEquals("Srbija", location.getCountry());
        assertFalse(location.isDeleted());
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdate() throws ObjectNotFoundException {
        Location location = locationService.getOne(1L);
        String locationName = location.getName();
        location.setName("Novo ime");
        Location updatedLocation = locationService.update(location);

        assertNotEquals(locationName, updatedLocation.getName());
        assertEquals("Novo ime", updatedLocation.getName());
    }

    @Test
    @Transactional
    @Rollback
    public void testDelete() throws ObjectNotFoundException {
        Location loc = new Location("Kupaliste", "Kralja Petra",
                32, "Novi Sad", "21000", "Srbija");
        Location savedLocation = repository.save(loc);
        boolean deleted = locationService.delete(savedLocation.getId());

        assertTrue(deleted);
    }
}
