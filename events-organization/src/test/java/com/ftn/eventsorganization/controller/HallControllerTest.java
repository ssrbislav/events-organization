package com.ftn.eventsorganization.controller;

import com.ftn.eventsorganization.DTO.HallDTO;
import com.ftn.eventsorganization.TestUtil;
import com.ftn.eventsorganization.model.Hall;
import com.ftn.eventsorganization.model.Location;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class HallControllerTest {

    private static final String URL_PREFIX = "/api/hall";
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostConstruct
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getAllTest() throws Exception {
        mockMvc.perform(get(URL_PREFIX)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[*].id").value(CoreMatchers.hasItem(1)))
                .andExpect(jsonPath("$.[*].name").value(CoreMatchers.hasItem("Master centar")))
                .andExpect(jsonPath("$.[*].deleted").value(CoreMatchers.hasItem(false)));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getOne() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/1")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Master centar"))
                .andExpect(jsonPath("$.deleted").value("false"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getOneTestNoHall() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/55"))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(roles = "ADMIN")
    public void createTest() throws Exception {
        HallDTO hallDTO = new HallDTO("Ime", 2L);
        String json = TestUtil.json(hallDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(roles = "ADMIN")
    public void createTestNoName() throws Exception {
        HallDTO hallDTO = new HallDTO("", 2L);
        String json = TestUtil.json(hallDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(roles = "ADMIN")
    public void createTestNoLocation() throws Exception {
        HallDTO hallDTO = new HallDTO("Ime", 26L);
        String json = TestUtil.json(hallDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(roles = "ADMIN")
    public void updateTest() throws Exception {
        Location location = new Location(1L, "Sajam", "Kralja Petra",
                30, "Novi Sad", "21000", "Srbija");
        Hall hall = new Hall("Im3e", location);
        hall.setId(1L);
        String json = TestUtil.json(hall);
        this.mockMvc.perform(put(URL_PREFIX + "/update").contentType(contentType).content(json))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(roles = "ADMIN")
    public void deleteTest() throws Exception {
        this.mockMvc.perform(delete(URL_PREFIX + "/delete/2"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(roles = "ADMIN")
    public void deleteTestNoHall() throws Exception {
        this.mockMvc.perform(delete(URL_PREFIX + "/delete/100"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("false"));
    }
}
