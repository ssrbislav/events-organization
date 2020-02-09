package com.ftn.eventsorganization.Service;

import com.ftn.eventsorganization.DTO.HallDTO;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Hall;
import com.ftn.eventsorganization.model.Location;
import com.ftn.eventsorganization.repository.HallRepository;
import com.ftn.eventsorganization.repository.LocationRepository;
import com.ftn.eventsorganization.service.IHallService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
@Rollback
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HallServiceTest {

    @Autowired
    IHallService hallService;

    ILocationService locationService;

    @Test
    public void testFindAll() {
        List<Hall> halls = hallService.findAll();

        assertNotNull(halls);
        assertThat(halls).hasSize(4);
    }

    @Test
    public void testGetOne() throws ObjectNotFoundException {
        Hall hall = hallService.getOne(1L);

        assertEquals((Long) 1L, hall.getId());
        assertEquals("Master centar", hall.getName());
        assertEquals((Long) 1L, hall.getLocation().getId());
        assertFalse(hall.isDeleted());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testObjectNotFound() throws ObjectNotFoundException {
        hallService.getOne(50L);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testLocationNotFound() throws Exception {
        HallDTO dto = new HallDTO("VIi", 22L);
        Hall hall = hallService.create(dto);
    }

    @Test(expected = InvalidInputException.class)
    public void testNameMustBePresent() throws Exception {
        HallDTO dto = new HallDTO("", 1L);
        Hall hall = hallService.create(dto);
    }

    @Test
    public void testCreateSuccessfull() throws Exception {
        HallDTO dto = new HallDTO("Validno ime", 2L);
        Hall hall = hallService.create(dto);

        assertNotNull(hall);
        assertEquals("Validno ime", hall.getName());
        assertFalse(hall.isDeleted());
    }

    @Test
    public void testUpdate() throws ObjectNotFoundException {
        Hall hall = hallService.getOne(1L);
        String hallName = hall.getName();
        hall.setName("Nova Hala");
        Hall updatedhall = hallService.update(hall);

        assertNotEquals(hallName, updatedhall.getName());
        assertEquals("Nova Hala", updatedhall.getName());
    }

    @Test
    public void testDelete() throws ObjectNotFoundException {
        boolean deleted = hallService.delete(1L);

        assertTrue(deleted);
    }
}
