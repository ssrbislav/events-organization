package com.ftn.eventsorganization.repository;

import com.fasterxml.jackson.annotation.OptBoolean;
import com.ftn.eventsorganization.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByName(String name);

    Optional<Event> findByName(String name);

    List<Event> findAllByDeletedIsFalse();

    Optional<Event> findById(Long id);
}
