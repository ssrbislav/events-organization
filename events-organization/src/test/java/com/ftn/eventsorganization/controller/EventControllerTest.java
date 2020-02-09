package com.ftn.eventsorganization.controller;

import com.ftn.eventsorganization.DTO.EventDTO;
import com.ftn.eventsorganization.TestUtil;
import com.ftn.eventsorganization.enumeration.EventType;
import com.ftn.eventsorganization.model.Event;
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
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class EventControllerTest {

    private static final String URL_PREFIX = "/api/event";
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
                .andExpect(jsonPath("$.[*].id").value(CoreMatchers.hasItem(2)))
                .andExpect(jsonPath("$.[*].name").value(CoreMatchers.hasItem("VIP Fest")))
                .andExpect(jsonPath("$.[*].startDate").value(CoreMatchers.hasItem("2020-10-10")))
                .andExpect(jsonPath("$.[*].endDate").value(CoreMatchers.hasItem("2020-10-15")))
                .andExpect(jsonPath("$.[*].eventType").value(CoreMatchers.hasItem("FAIR")))
                .andExpect(jsonPath("$.[*].deleted").value(CoreMatchers.hasItem(false)));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getOne() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/1")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("VIP Fest"))
                .andExpect(jsonPath("$.startDate").value("2020-02-02"))
                .andExpect(jsonPath("$.endDate").value("2020-02-08"))
                .andExpect(jsonPath("$.eventType").value("FESTIVAL"))
                .andExpect(jsonPath("$.deleted").value(false));
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(roles = "ADMIN")
    public void createTest() throws Exception {
        String valuee = "2021-02-02";
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(valuee);
        String value = "2021-02-09";
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(value);

        EventDTO eventDTO = new EventDTO("Dogadjaj", startDate, endDate, EventType.FAIR, 1L);
        String json = TestUtil.json(eventDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.name").value("Dogadjaj"))
                .andExpect(jsonPath("$.startDate").value("2021-02-01"))
                .andExpect(jsonPath("$.endDate").value("2021-02-08"))
                .andExpect(jsonPath("$.eventType").value("FAIR"))
                .andExpect(jsonPath("$.deleted").value(false))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(roles = "ADMIN")
    public void createTestWrongDate() throws Exception {
        String valuee = "2020-01-01";
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(valuee);
        String value = "2020-01-09";
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(value);

        EventDTO eventDTO = new EventDTO("Dogadjaj", startDate, endDate, EventType.FAIR, 1L);
        String json = TestUtil.json(eventDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(roles = "ADMIN")
    public void createTestNoLocation() throws Exception {
        String valuee = "2021-01-01";
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(valuee);
        String value = "2021-01-09";
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(value);

        EventDTO eventDTO = new EventDTO("Dogadjaj", startDate, endDate, EventType.FAIR, 19L);
        String json = TestUtil.json(eventDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(roles = "ADMIN")
    public void updateTest() throws Exception {
        Location location = new Location(1L, "Arena", "Bulevar", 30, "Beograd", "11000", "Srbija");

        String valuee = "2020-02-02";
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(valuee);
        String value = "2020-02-08";
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(value);

        Event event = new Event("Koncert Ponovo", startDate, endDate, EventType.FESTIVAL, location);
        event.setId(1L);

        String json = TestUtil.json(event);
        this.mockMvc.perform(put(URL_PREFIX + "/update").contentType(contentType).content(json)).andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Koncert Ponovo"))
                .andExpect(jsonPath("$.startDate").value("2020-02-01"))
                .andExpect(jsonPath("$.endDate").value("2020-02-07"))
                .andExpect(jsonPath("$.eventType").value("FESTIVAL"))
                .andExpect(jsonPath("$.deleted").value(false))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(roles = "ADMIN")
    public void deleteTest() throws Exception {
        this.mockMvc.perform(delete(URL_PREFIX + "/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(roles = "ADMIN")
    public void deleteTestNoEvent() throws Exception {
        this.mockMvc.perform(delete(URL_PREFIX + "/delete/100"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("false"));
    }
}
