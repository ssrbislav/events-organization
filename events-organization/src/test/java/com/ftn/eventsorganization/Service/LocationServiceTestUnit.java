package com.ftn.eventsorganization.Service;

import com.ftn.eventsorganization.DTO.LocationDTO;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Location;
import com.ftn.eventsorganization.repository.LocationRepository;
import com.ftn.eventsorganization.service.ILocationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class LocationServiceTestUnit {

    @Autowired
    private ILocationService locationService;

    @MockBean
    private LocationRepository locationRepository;

    @Before
    public void setUp() throws ObjectNotFoundException {
        Location location = new Location("Lokacija", "Gogoljeva", 30, "Novi Sad", "21000", "Srbija");
        Mockito.when(locationRepository.findByIdAndDeletedIsFalse(1L)).thenReturn(Optional.of(location));

        List<Location> locations = new ArrayList<>();
        locations.add(location);
        locations.add(new Location("Arena", "Bulevar", 50, "Beograd", "11000", "Srbija"));
        locations.add(new Location("OK", "Marka Kraljevica", 500, "Sabac", "11000", "Srbija"));
        Mockito.when(locationRepository.findAllByDeletedIsFalse()).thenReturn(locations);

        Mockito.when(locationRepository.findByIdAndDeletedIsFalse(10L)).thenReturn(null);

        Location sLocation = new Location(5L, "Arena", "Tolstojeva", 30, "Novi Sad", "21000", "Srbija");

        Mockito.when(locationRepository.findByIdAndDeletedIsFalse(5L)).thenReturn(Optional.of(sLocation));
        Mockito.when(locationRepository.save(sLocation)).thenReturn(sLocation);
    }

    @Test
    public void testFindAll() {
        List<Location> locations = locationService.findAll();

        assertNotNull(locations);
        assertThat(locations).hasSize(3);
    }

    @Test(expected = NullPointerException.class)
    public void testNull() throws ObjectNotFoundException {
        Location location = locationService.getOne(10L);
    }

    @Test(expected = InvalidInputException.class)
    public void testInvalidInput() throws InvalidInputException {
        LocationDTO dto = new LocationDTO(null, "Kralja Petra",
                32, "Novi Sad", "21000", "Srbija");
        Location location = locationService.create(dto);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testObjectNotFound() throws ObjectNotFoundException {
        Location location = locationService.getOne(11L);
    }

    @Test
    public void testGetOne() throws ObjectNotFoundException {
        Location location = locationService.getOne(1L);

        assertNotNull(location);
        assertEquals("Novi Sad", location.getCity());
        assertEquals("Srbija", location.getCountry());
        assertEquals("21000", location.getZipCode());
        assertEquals(30, location.getNumber());
        assertEquals("Lokacija", location.getName());
        assertEquals("Gogoljeva", location.getStreetName());
        assertFalse(location.isDeleted());
    }

    @Test
    public void testUpdate() throws ObjectNotFoundException {
        Location loc = locationService.getOne(5L);
        loc.setName("Update");
        Location location = locationService.update(loc);

        assertNotNull(location);
        assertEquals((Long) 5L, location.getId());
        assertEquals("Update", location.getName());
    }

    @Test
    public void deleteLocation() throws ObjectNotFoundException {
        boolean deleted = locationService.delete(1L);

        assertTrue(deleted);
    }
}
