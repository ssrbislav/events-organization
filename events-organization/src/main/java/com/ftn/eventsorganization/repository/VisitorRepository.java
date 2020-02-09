package com.ftn.eventsorganization.repository;

import com.ftn.eventsorganization.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {

    Optional<Visitor> findByUsername(String username);
}
