package com.ftn.eventsorganization.service.impl;

import com.ftn.eventsorganization.enumeration.RoleType;
import com.ftn.eventsorganization.model.Admin;
import com.ftn.eventsorganization.model.Role;
import com.ftn.eventsorganization.repository.AdminRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class AppLoaderService implements ApplicationRunner {

    AdminRepository adminRepository;

    public AppLoaderService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(adminRepository.findAll().size() == 0) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            Role role = new Role();
            role.setType(RoleType.ADMIN);
            Set<Role> roles = new HashSet<>();
            roles.add(role);

            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setUsername(encoder.encode("admin"));
            admin.setEmail("admin@admin.com");
            admin.setFirstName("");
            admin.setLastName("");
            admin.setPhoneNumber("");
            admin.setDateOfBirth(new Date());
            admin.setRoles(roles);
            adminRepository.save(admin);
            System.out.println("Admin created!");
        }
    }
}
