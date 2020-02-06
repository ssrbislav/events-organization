package com.ftn.eventsorganization.repository;

import com.ftn.eventsorganization.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SectorRepository extends JpaRepository<Sector, Long> {

    List<Sector> findAllByDeletedIsFalse();

    Optional<Sector> findById(Long id);

    Optional<Sector> findByIdAndDeletedIsFalse(Long id);
    
    Optional<Sector> findBySectorMark(String s);
}
