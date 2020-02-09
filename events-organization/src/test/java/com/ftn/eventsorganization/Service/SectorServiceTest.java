package com.ftn.eventsorganization.Service;

import com.ftn.eventsorganization.DTO.SectorDTO;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Sector;
import com.ftn.eventsorganization.service.ISectorService;
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
@Rollback
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SectorServiceTest {

    @Autowired
    ISectorService sectorService;

    @Test
    public void testFindAll() {
        List<Sector> sectors = sectorService.findAll();

        assertNotNull(sectors);
        assertFalse(sectors.isEmpty());
        assertThat(sectors).hasSize(4);
    }

    @Test
    public void testGetOne() throws ObjectNotFoundException {
        Sector sector = sectorService.getOne(1L);

        assertNotNull(sector);
        assertEquals((Long) 4L, sector.getNumOfColumns());
        assertEquals((Long) 4L, sector.getNumOfRows());
        assertEquals("SEC444", sector.getSectorMark());
        assertEquals((Long) 1L, sector.getHall().getId());
        assertFalse(sector.isDeleted());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testHallNotFound() throws InvalidInputException, ObjectNotFoundException {
        SectorDTO sectorDTO = new SectorDTO("SEC1", 4l, 6L, 50L);
        sectorService.create(sectorDTO);
    }

    @Test(expected = InvalidInputException.class)
    public void testWrongNumberOfRowsAndColumns() throws InvalidInputException, ObjectNotFoundException {
        SectorDTO sectorDTO = new SectorDTO("SEC1", -4l, 0L, 1L);
        sectorService.create(sectorDTO);
    }

    @Test
    public void testCreateSuccesfully() throws InvalidInputException, ObjectNotFoundException {
        SectorDTO dto = new SectorDTO("SEC555", 5L, 5L, 2L);
        Sector sector = sectorService.create(dto);
    }

    @Test
    public void testUpdate() throws ObjectNotFoundException, InvalidInputException {
        Sector sector = sectorService.getOne(2L);
        sector.setSectorMark("GI9");
        Sector sectorUpdate = sectorService.update(sector);

        assertEquals("GI9", sectorUpdate.getSectorMark());
    }

    @Test
    public void testDelete() throws ObjectNotFoundException {
        boolean deleted = sectorService.delete(sectorService.getOne(1L).getId());

        assertTrue(deleted);
    }
}