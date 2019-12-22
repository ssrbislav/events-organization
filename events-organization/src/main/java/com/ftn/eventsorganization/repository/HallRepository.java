package com.ftn.eventsorganization.repository;

import com.ftn.eventsorganization.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {

    List<Hall> findAllByDeletedIsFalse();

    Optional<Hall> findById(Long id);
}
