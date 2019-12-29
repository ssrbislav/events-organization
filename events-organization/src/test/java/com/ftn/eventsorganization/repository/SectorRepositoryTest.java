package com.ftn.eventsorganization.repository;

import com.ftn.eventsorganization.model.Hall;
import com.ftn.eventsorganization.model.Location;
import com.ftn.eventsorganization.model.Sector;
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
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@Rollback(value = true)
public class SectorRepositoryTest {

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Before
    public void setUp() {
        Location location = new Location();
        location.setName("Sajam");
        location.setStreetName("Novosadskog sajma");
        location.setNumber(30);
        location.setCountry("Srbija");
        location.setZipCode("21000");
        Location loc = locationRepository.save(location);

        Hall hall = new Hall();
        hall.setName("Arena");
        hall.setLocation(loc);
        hallRepository.save(hall);
    }

    @Test
    @Transactional
    @Rollback
    public void testSectorSave() {
        Sector sector = new Sector();
        sector.setSectorMark("Sector 3");
        sector.setNumOfColumns(4L);
        sector.setNumOfRows(4L);
        sector.setHall(hallRepository.getOne(1L));

        Sector savedSector = sectorRepository.save(sector);

        assertEquals(false, savedSector.isDeleted());
        assertEquals(sector.getSectorMark(), savedSector.getSectorMark());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testSaveEmpty() {
        sectorRepository.save(new Sector());
    }

    @Test
    @Transactional
    @Rollback
    public void testFindAllByDeletedIsFalse() {
        List<Sector> sectors = sectorRepository.findAllByDeletedIsFalse();

        assertNotNull(sectors);
        assertFalse(sectors.isEmpty());
    }

    @Test
    @Transactional
    @Rollback
    public void testFindByIdAndDeletedIsFalse() {
        Sector sector = new Sector();
        sector.setSectorMark("Sector 3");
        sector.setNumOfColumns(4L);
        sector.setNumOfRows(4L);
        sector.setHall(hallRepository.getOne(1L));

        Sector savedSector = sectorRepository.save(sector);

        assertNotNull(sector);
        assertFalse(savedSector.isDeleted());
    }

}
