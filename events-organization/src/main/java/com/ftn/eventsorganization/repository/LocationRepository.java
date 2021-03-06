package com.ftn.eventsorganization.repository;

import com.ftn.eventsorganization.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<Location> findByIdAndDeletedIsFalse(Long id);

    Optional<Location> findByNameAndDeletedIsFalse(String name);

    List<Location> findAllByDeletedIsFalse();
}
