package com.ftn.eventsorganization.repository;

import com.ftn.eventsorganization.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findAllByName(String name);
}
