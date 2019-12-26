package com.ftn.eventsorganization.repository;

import com.ftn.eventsorganization.model.Location;
import com.sun.tools.jconsole.JConsoleContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@Rollback(value = true)
public class LocationRepositoryTest {

    @Autowired
    LocationRepository locationRepository;

    @Test
    @Transactional
    @Rollback
    public void testLocationSave() {

        Location location = new Location();
        location.setName("Sajam");
        location.setStreetName("Novosadskog sajma");
        location.setNumber(30);
        location.setCountry("Srbija");
        location.setZipCode("21000");

        Location savedLocation = locationRepository.save(location);

        assertEquals(false, savedLocation.isDeleted());
        assertEquals(location.getName(), savedLocation.getName());
    }

    @Test
    @Rollback
    public void testFindByNameAndDeletedIsFalse() {
        Location location = new Location();
        location.setName("Lokacija");
        location.setStreetName("Adresa lokacije");
        location.setNumber(30);
        location.setCountry("Srbija");
        location.setZipCode("21000");

        locationRepository.save(location);

        assertEquals(location.getName(), locationRepository.findByNameAndDeletedIsFalse(location.getName()).get().getName());
    }

    @Test
    public void testfindByIdAndDeletedIsFalse() {
        Location location = new Location();
        location.setName("Lokacija");
        location.setStreetName("Adresa lokacije");
        location.setNumber(30);
        location.setCountry("Srbija");
        location.setZipCode("21000");

        Location l = locationRepository.save(location);

        assertEquals(l.getId(), locationRepository.findByIdAndDeletedIsFalse(l.getId()).get().getId());
        assertEquals(false, locationRepository.findByIdAndDeletedIsFalse(l.getId()).get().isDeleted());
    }

    @Test
    public void testfindAllByDeletedIsFalse() {

        List<Location> locations = locationRepository.findAllByDeletedIsFalse();

        assertNotNull(locations);
        assertFalse(locations.isEmpty());
    }

}
