package com.ftn.eventsorganization.Service;

import com.ftn.eventsorganization.DTO.LocationDTO;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Location;
import com.ftn.eventsorganization.service.ILocationService;
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

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    @Transactional
    @Rollback
    public void testFindAll() {
        List<Location> locations = locationService.findAll();

        assertNotNull(locations);
        assertThat(locations).hasSize(4);
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
        Location location = locationService.getOne(2L);

        assertNotNull(location);
        assertEquals((Long) 2L, location.getId());
        assertEquals("Sumice", location.getName());
        assertEquals("Bulevar", location.getStreetName());
        assertEquals(30, location.getNumber());
        assertEquals("Beograd", location.getCity());
        assertEquals("11000", location.getZipCode());
        assertEquals("Srbija", location.getCountry());
        assertFalse(location.isDeleted());
    }

    @Test(expected = InvalidInputException.class)
    @Transactional
    @Rollback
    public void testSameNameError() throws InvalidInputException {
        LocationDTO dto = new LocationDTO("Sumice", "Kralja Petra",
                32, "Novi Sad", "21000", "Srbija");
        locationService.create(dto);
    }

    @Test(expected = InvalidInputException.class)
    @Transactional
    @Rollback
    public void testNoNameError() throws InvalidInputException {
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
        LocationDTO dto = new LocationDTO("Kupaliste", "Kralja Petra",
                32, "Novi Sad", "21000", "Srbija");
        Location location = locationService.create(dto);

        assertNotNull(location);
        assertEquals("Kupaliste", location.getName());
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
    public void testDelete() throws ObjectNotFoundException, InvalidInputException {
        boolean deleted = locationService.delete(locationService.getOne(1L).getId());

        assertTrue(deleted);
    }
}
