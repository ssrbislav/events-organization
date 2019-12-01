package com.ftn.eventsorganization.repository;

import com.ftn.eventsorganization.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByType(String roleType);
}
