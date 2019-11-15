package com.ftn.eventsorganization.repository;

import com.ftn.eventsorganization.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findAllByName(String name);
    List<Location> findAll();
    Optional<Location> findById(Long id);
}
