package com.ftn.eventsorganization.controller;

import com.ftn.eventsorganization.DTO.EventSectorDTO;
import com.ftn.eventsorganization.TestUtil;
import com.ftn.eventsorganization.enumeration.SectorType;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class EventSectorControllerTest {

    private static final String URL_PREFIX = "/api/event_sector";
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
                .andExpect(jsonPath("$.[*].price").value(CoreMatchers.hasItem(330.0)))
                .andExpect(jsonPath("$.[*].sectorType").value(CoreMatchers.hasItem("REGULAR")));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getOne() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/1")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.price").value(200.0))
                .andExpect(jsonPath("$.sectorType").value("VIP"));
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(roles = "ADMIN")
    public void createTest() throws Exception {
        EventSectorDTO eventSectorDTO = new EventSectorDTO(1L, 1L, 450.0, SectorType.LOVE);
        String json = TestUtil.json(eventSectorDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.price").value(450.0))
                .andExpect(jsonPath("$.sectorType").value("LOVE"))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(roles = "ADMIN")
    public void createTestNoSector() throws Exception {
        EventSectorDTO eventSectorDTO = new EventSectorDTO(17L, 1L, 450.0, SectorType.LOVE);
        String json = TestUtil.json(eventSectorDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(roles = "ADMIN")
    public void createTestNoEvent() throws Exception {
        EventSectorDTO eventSectorDTO = new EventSectorDTO(1L, 18L, 450.0, SectorType.LOVE);
        String json = TestUtil.json(eventSectorDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(roles = "ADMIN")
    public void createTestBadPrice() throws Exception {
        EventSectorDTO eventSectorDTO = new EventSectorDTO(1L, 1L, -8.0, SectorType.LOVE);
        String json = TestUtil.json(eventSectorDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
                .andExpect(status().isBadRequest());
    }
}
