package com.hexaware.CozyHeavenStay.controller;

import com.hexaware.CozyHeavenStay.dto.ReservationDTO;
import com.hexaware.CozyHeavenStay.dto.ReservationRequestDTO;
import com.hexaware.CozyHeavenStay.entities.Reservation;
import com.hexaware.CozyHeavenStay.service.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;


    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;

    }

    @PostMapping("/make")
    public ResponseEntity<List<Reservation>> makeReservation(@Valid @RequestBody ReservationRequestDTO request) {
        List<Reservation> reservations = reservationService.makeReservation(
                request.getHotelId(),
                request.getUserId(),
                request.getRoomType(),
                request.getBedType(),
                request.isAcStatus(),
                request.getCheckInDate(),
                request.getCheckOutDate(),
                request.getNoOfRooms(),
                request.getNoOfAdults(),
                request.getNoOfChildren(),
                request.getTotalFare()
        );
        return new ResponseEntity<>(reservations, HttpStatus.CREATED);
    }


    @GetMapping("/getbyid/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        ReservationDTO reservationDTO = reservationService.getReservationById(id);
        if (reservationDTO != null) {
            return new ResponseEntity<>(reservationDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    @GetMapping("/getall")
    public List<ReservationDTO> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable Long id, @RequestBody ReservationDTO reservationDTO) {
        ReservationDTO updatedReservation = reservationService.updateReservation(id, reservationDTO);
        if (updatedReservation != null) {
            return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
