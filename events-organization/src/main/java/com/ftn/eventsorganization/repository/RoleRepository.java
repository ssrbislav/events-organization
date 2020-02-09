package com.ftn.eventsorganization.repository;

import com.ftn.eventsorganization.enumeration.RoleType;
import com.ftn.eventsorganization.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByType(RoleType roleType);
}
