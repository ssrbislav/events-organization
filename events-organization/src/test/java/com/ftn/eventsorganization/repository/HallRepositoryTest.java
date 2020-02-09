package com.ftn.eventsorganization.repository;

import com.ftn.eventsorganization.model.Hall;
import com.ftn.eventsorganization.model.Location;
import org.apache.tomcat.jni.Local;
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
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@Rollback(value = true)
public class HallRepositoryTest {

    @Autowired
    HallRepository hallRepository;

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
        Location l = locationRepository.save(location);
    }

    @Test
    @Transactional
    @Rollback
    public void testSaveHall() {
        Location location = new Location();
        location.setName("Sajam");
        location.setStreetName("Novosadskog sajma");
        location.setNumber(30);
        location.setCountry("Srbija");
        location.setZipCode("21000");
        Location l = locationRepository.save(location);

        Hall hall = new Hall();
        hall.setName("Master");
        hall.setLocation(l);
        Hall savedHall = hallRepository.save(hall);

        assertNotNull(savedHall);
        assertEquals(hall.getName(), savedHall.getName());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testSaveEmpty() {
        hallRepository.save(new Hall());
    }

    @Test
    @Transactional
    @Rollback
    public void testFindByIdAndDeletedIsFalse() {
        Optional<Location> location = locationRepository.findById(1L);
        Hall hall = new Hall();
        hall.setLocation(location.get());
        hall.setName("Arena");

        Hall savedHall = hallRepository.save(hall);

        assertEquals(hall.getId(), hallRepository.findByIdAndDeletedIsFalse(hall.getId()).get().getId());
        assertEquals(false, savedHall.isDeleted());
    }

    @Test
    @Transactional
    @Rollback
    public void testFindAllByDeletedIsFalse() {
        List<Hall> halls = hallRepository.findAllByDeletedIsFalse();

        assertNotNull(halls);
        assertFalse(halls.isEmpty());
    }

}
