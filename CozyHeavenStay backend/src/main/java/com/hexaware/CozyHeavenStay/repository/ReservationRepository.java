package com.hexaware.CozyHeavenStay.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.CozyHeavenStay.entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	  List<Reservation> findByCheckOutDateBeforeAndStatus(LocalDateTime checkOutDate, String status);

	List<Reservation> findByUserUserId(Long userId);   
	
}

