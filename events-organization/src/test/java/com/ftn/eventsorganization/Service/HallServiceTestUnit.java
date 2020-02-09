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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class HallServiceTestUnit {

    @Autowired
    private IHallService hallService;

    @MockBean
    private HallRepository hallRepository;

    @MockBean
    private LocationRepository locationRepository;

    @Before
    public void setUp() {
        Location location = new Location(1L, "Lokacija", "Gogoljeva", 30, "Novi Sad", "21000", "Srbija");
        Mockito.when(locationRepository.findByIdAndDeletedIsFalse(1L)).thenReturn(Optional.of(location));

        Hall hall1 = new Hall("Hala", location);
        hall1.setId(1L);
        Hall hall2 = new Hall("Hala2", location);
        Hall hall3 = new Hall("Hala3", location);
        List<Hall> halls = new ArrayList<>();
        halls.add(hall1);
        halls.add(hall2);
        halls.add(hall3);

        Mockito.when(hallRepository.findByIdAndDeletedIsFalse(1L)).thenReturn(Optional.of(hall1));
        Mockito.when(hallRepository.findAllByDeletedIsFalse()).thenReturn(halls);

        Mockito.when(hallRepository.findByIdAndDeletedIsFalse(50L)).thenReturn(null);

        Location location2 = new Location("Arena", "Bulevar", 30, "Beograd", "11000", "Srbija");

        Hall hall4 = new Hall();
        hall4.setId(4L);
        hall4.setName("Master");
        hall4.setLocation(location2);
        Mockito.when(hallRepository.findByIdAndDeletedIsFalse(4L)).thenReturn(Optional.of(hall4));
        Mockito.when(hallRepository.save(hall4)).thenReturn(hall4);
    }

    @Test
    public void testFindAll() {
        List<Hall> halls = hallService.findAll();

        assertNotNull(halls);
        assertThat(halls).hasSize(3);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testObjectNotFound() throws ObjectNotFoundException {
        Hall hall = hallService.getOne(60L);
    }

    @Test(expected = NullPointerException.class)
    public void testObjectNull() throws ObjectNotFoundException {
        Hall hall = hallService.getOne(50L);
    }

    @Test
    public void testGetOne() throws ObjectNotFoundException {
        Hall hall = hallService.getOne(1L);

        assertNotNull(hall);
        assertThat(hall.isDeleted()).isFalse();
        assertEquals((Long) 1L, hall.getId());
        assertEquals("Hala", hall.getName());
        assertEquals((Long) 1L, hall.getLocation().getId());
    }

    @Test(expected = InvalidInputException.class)
    public void testInvalidInput() throws Exception {
        HallDTO dto = new HallDTO("", 1L);
        Hall hall = hallService.create(dto);
    }

    @Test
    public void testUpdate() throws ObjectNotFoundException {
        Hall hall = hallService.getOne(1L);
        hall.setName("Novo");
        Hall savedHall = hallService.update(hall);

        assertNotNull(hall);
        assertEquals("Novo", hall.getName());
    }

    @Test
    public void testDelete() throws ObjectNotFoundException {
        Hall hall = hallService.getOne(1L);
        boolean deleted = hallService.delete(hall.getId());

        assertThat(deleted).isTrue();
    }
}
