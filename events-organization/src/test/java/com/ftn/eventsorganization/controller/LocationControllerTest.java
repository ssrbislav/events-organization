package com.ftn.eventsorganization.controller;

import com.ftn.eventsorganization.DTO.LocationDTO;
import com.ftn.eventsorganization.TestUtil;
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
public class LocationControllerTest {

    private static final String URL_PREFIX = "/api/location";
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
                .andExpect(jsonPath("$.[*].name").value(CoreMatchers.hasItem("Sajam")))
                .andExpect(jsonPath("$.[*].streetName").value(CoreMatchers.hasItem("Kralja Petra")))
                .andExpect(jsonPath("$.[*].number").value(CoreMatchers.hasItem(30)))
                .andExpect(jsonPath("$.[*].city").value(CoreMatchers.hasItem("Novi Sad")))
                .andExpect(jsonPath("$.[*].zipCode").value(CoreMatchers.hasItem("21000")));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getOneTest() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/2")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("Sumice"))
                .andExpect(jsonPath("$.streetName").value("Bulevar"))
                .andExpect(jsonPath("$.number").value(30))
                .andExpect(jsonPath("$.city").value("Beograd"))
                .andExpect(jsonPath("$.zipCode").value("11000"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getOneTestNoLocation() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/55"))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(roles = "ADMIN")
    public void createTest() throws Exception {
        LocationDTO locationDTO = new LocationDTO("Loki", "Kralja Petra",
                32, "Novi Sad", "21000", "Srbija");
        String json = TestUtil.json(locationDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(roles = "ADMIN")
    public void createBadRequestTest() throws Exception {
        LocationDTO locationDTO = new LocationDTO("Sumice", "Kralja Petra",
                32, "Novi Sad", "21000", "Srbija");
        String json = TestUtil.json(locationDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
                .andExpect(status().isBadRequest());

    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(roles = "ADMIN")
    public void updateNotFoundTest() throws Exception {
        Location location = new Location(1L, "Sajam2", "Kralja",
                32, "Novi Sad", "21000", "Srbija");
        String json = TestUtil.json(location);
        this.mockMvc.perform(put(URL_PREFIX + "/update").contentType(contentType).content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(roles = "ADMIN")
    public void deleteTest_OK() throws Exception {
        this.mockMvc.perform(delete(URL_PREFIX + "/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(roles = "ADMIN")
    public void deleteTest_NoLocation() throws Exception {
        this.mockMvc.perform(delete(URL_PREFIX + "/delete/100"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("false"));
    }
}
