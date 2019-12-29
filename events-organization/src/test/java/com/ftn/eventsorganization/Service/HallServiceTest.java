package com.ftn.eventsorganization.Service;

import com.ftn.eventsorganization.DTO.HallDTO;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Hall;
import com.ftn.eventsorganization.model.Location;
import com.ftn.eventsorganization.repository.HallRepository;
import com.ftn.eventsorganization.repository.LocationRepository;
import com.ftn.eventsorganization.service.IHallService;
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
public class HallServiceTest {

    @Autowired
    IHallService hallService;

    @Autowired
    HallRepository hallRepository;

    @Autowired
    LocationRepository locationRepository;

    @Before
    public void setUp() {
        Location location = new Location("Sajmiste", "Novosadskog sajma", 30, "Novi Sad", "21000", "Srbija");
        Location l = locationRepository.save(location);
        Hall hall = new Hall("Beogradska arena", l);
        hallRepository.save(hall);
    }

    @Test
    @Transactional
    @Rollback
    public void testFindAll() {
        List<Hall> halls = hallService.findAll();

        assertNotNull(halls);
        assertFalse(halls.isEmpty());
    }

    @Test(expected = ObjectNotFoundException.class)
    @Transactional
    @Rollback
    public void testObjectNotFound() throws ObjectNotFoundException {
        hallService.getOne(50L);
    }

    @Test(expected = ObjectNotFoundException.class)
    @Transactional
    @Rollback
    public void testLocationNotFound() throws Exception {
        HallDTO dto = new HallDTO("VIi", 22L);
        Hall hall = hallService.create(dto);
    }

    @Test(expected = InvalidInputException.class)
    @Transactional
    @Rollback
    public void testNameMustBePresent() throws Exception {
        HallDTO dto = new HallDTO("", 1L);
        Hall hall = hallService.create(dto);
    }

    @Test
    @Transactional
    @Rollback
    public void testCreateSuccessfull() throws Exception {
        HallDTO dto = new HallDTO("Validno ime", 1L);
        Hall hall = hallService.create(dto);

        assertNotNull(hall);
        assertEquals("Validno ime", hall.getName());
        assertEquals(locationRepository.findByIdAndDeletedIsFalse(1L).get().getId(), hall.getLocation().getId());
        assertFalse(hall.isDeleted());
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdate() throws ObjectNotFoundException {
        Hall hall = hallService.getOne(1L);
        String hallName = hall.getName();
        hall.setName("Nova Hala");
        Hall updatedhall = hallService.update(hall);

        assertNotEquals(hallName, updatedhall.getName());
        assertEquals("Nova Hala", updatedhall.getName());
    }

    @Test
    @Transactional
    @Rollback
    public void testDelete() throws ObjectNotFoundException {
        Hall hall = new Hall("Sajam", locationRepository.getOne(1L));
        Hall savedHall = hallRepository.save(hall);
        boolean deleted = hallService.delete(savedHall.getId());

        assertTrue(deleted);
    }
}
