package com.hexaware.CozyHeavenStay.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.hexaware.CozyHeavenStay.dto.ReservationDTO;
import com.hexaware.CozyHeavenStay.dto.ReservationRequestDTO;
import com.hexaware.CozyHeavenStay.dto.UserDTO;
import com.hexaware.CozyHeavenStay.entities.Reservation;
import com.hexaware.CozyHeavenStay.entities.Room;
import com.hexaware.CozyHeavenStay.entities.Room.RoomType;

public interface ReservationService {

    ReservationDTO getReservationById(Long id);

    List<ReservationDTO> getAllReservations();

    ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO);

    void deleteReservation(Long id);
  
	List<Reservation> makeReservation(Long hotelId, Long userId, RoomType roomType, String bedType, boolean acStatus,
			LocalDateTime checkInDate, LocalDateTime checkOutDate, int noOfRooms, int noOfAdults, int noOfChildren,
			double totalFare);

	 List<ReservationDTO> getUserReservations(Long userId);
	  
}

