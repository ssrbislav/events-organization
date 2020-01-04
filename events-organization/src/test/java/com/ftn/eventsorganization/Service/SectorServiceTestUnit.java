package com.ftn.eventsorganization.Service;

import com.ftn.eventsorganization.DTO.SectorDTO;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Hall;
import com.ftn.eventsorganization.model.Location;
import com.ftn.eventsorganization.model.Sector;
import com.ftn.eventsorganization.repository.HallRepository;
import com.ftn.eventsorganization.repository.LocationRepository;
import com.ftn.eventsorganization.repository.SectorRepository;
import com.ftn.eventsorganization.service.ISectorService;
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
public class SectorServiceTestUnit {

    @MockBean
    SectorRepository sectorRepository;

    @Autowired
    private ISectorService sectorService;

    @MockBean
    LocationRepository locationRepository;

    @MockBean
    HallRepository hallRepository;

    @Before
    public void setUp() {
        Location location = new Location(1L, "Arena", "Bulevar", 30, "Beograd", "11000", "Srbija");
        Mockito.when(locationRepository.findByIdAndDeletedIsFalse(1L)).thenReturn(Optional.of(location));

        Hall hall = new Hall("Hala1", location);
        hall.setId(1L);
        Mockito.when(hallRepository.findByIdAndDeletedIsFalse(1L)).thenReturn(Optional.of(hall));

        Sector sector = new Sector();
        sector.setId(1L);
        sector.setSectorMark("GG4TY");
        sector.setHall(hall);
        sector.setNumOfColumns(4L);
        sector.setNumOfRows(4L);
        Mockito.when(sectorRepository.findByIdAndDeletedIsFalse(1L)).thenReturn(Optional.of(sector));
        Mockito.when(sectorRepository.save(sector)).thenReturn(sector);

        Sector sector1 = new Sector("TTE", 4L, 5L, hall);
        Sector sector2 = new Sector("TOTO", 3L, 2L, hall);
        Sector sector3 = new Sector("RE3W", 6L, 6L, hall);

        Sector sector4 = new Sector("RE3W", 6L, 6L, hall);
        sector4.setId(4L);
        Mockito.when(sectorRepository.findByIdAndDeletedIsFalse(4L)).thenReturn(Optional.of(sector4));

        List<Sector> sectors = new ArrayList<>();
        sectors.add(sector1);
        sectors.add(sector2);
        sectors.add(sector3);
        Mockito.when(sectorRepository.findAllByDeletedIsFalse()).thenReturn(sectors);

        Mockito.when(sectorRepository.findByIdAndDeletedIsFalse(10L)).thenReturn(null);
    }

    @Test
    public void testFindAll() {
        List<Sector> sectors = sectorService.findAll();

        assertNotNull(sectors);
        assertThat(sectors).hasSize(3);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testObjectNotFound() throws ObjectNotFoundException {
        Sector sector = sectorService.getOne(11L);
    }

    @Test(expected = InvalidInputException.class)
    public void testInvalidInput() throws InvalidInputException, ObjectNotFoundException {
        SectorDTO dto = new SectorDTO("SEC3", 0L, 0L, 1L);
        sectorService.create(dto);
    }

    @Test(expected = NullPointerException.class)
    public void testNull() throws ObjectNotFoundException {
        Sector sector = sectorService.getOne(10L);
    }

    @Test
    public void testGetOne() throws ObjectNotFoundException {
        Sector sector = sectorService.getOne(1L);

        assertNotNull(sector);
        assertEquals("GG4TY", sector.getSectorMark());
        assertEquals((Long)1L, sector.getHall().getId());
        assertThat(sector.getNumOfColumns()).isEqualTo(4);
        assertThat(sector.getNumOfRows()).isEqualTo(4);
        assertThat(sector.isDeleted()).isFalse();
    }

    @Test
    public void testUpdate() throws ObjectNotFoundException, InvalidInputException {
        Sector sector = sectorService.getOne(1L);
        sector.setNumOfRows(9L);
        sector.setNumOfColumns(2L);
        Sector updateSector = sectorService.update(sector);

        assertNotNull(updateSector);
        assertEquals((Long)1L, updateSector.getId());
        assertEquals((Long) 9L, updateSector.getNumOfRows());
        assertEquals((Long) 2L, updateSector.getNumOfColumns());
        assertEquals("GG4TY", updateSector.getSectorMark());
    }

    @Test
    public void testDelete() throws ObjectNotFoundException {
        Sector sector = sectorService.getOne(4L);
        boolean deleted = sectorService.delete(sector.getId());

        assertThat(deleted).isTrue();
    }
}
