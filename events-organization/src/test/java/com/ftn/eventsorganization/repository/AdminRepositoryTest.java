package com.ftn.eventsorganization.repository;

import com.ftn.eventsorganization.model.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@Rollback(value = true)
public class AdminRepositoryTest {

    @Autowired
    private AdminRepository adminRepository;

    @Test
    @Transactional
    public void testIfAdminExists() {

        Admin admin = new Admin();
        admin.setUsername("admin2");
        admin.setPassword("password");
        admin.setAddress("");
        admin.setEmail("");
        admin.setFirstName("");
        admin.setLastName("");
        admin.setPhoneNumber("");
        admin.setDateOfBirth(new Date());
        adminRepository.save(admin);

        Optional<Admin> findAdmin = adminRepository.findById(admin.getId());

        assertNotNull(findAdmin);
        assertEquals(admin.getId(), findAdmin.get().getId());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testSaveEmptyAdmin() {
        adminRepository.save(new Admin());
    }

}
