package com.ftn.eventsorganization.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ftn.eventsorganization.model.Reservation;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	List<Reservation> findAllById(Long id);
	List<Reservation> findAllByExpireDate(Date d);

}
