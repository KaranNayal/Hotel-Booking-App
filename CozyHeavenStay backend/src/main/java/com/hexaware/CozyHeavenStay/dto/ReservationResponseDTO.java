package com.hexaware.CozyHeavenStay.dto;

import java.time.LocalDateTime;

public class ReservationResponseDTO {
    private Long reservationId;
    private Long roomId;
    private int noOfRooms;
    private int noOfChildrens;
    private int noOfAdults;
    private String status;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private double totalFare;

    public ReservationResponseDTO() {
    }

    public ReservationResponseDTO(Long reservationId, Long roomId, int noOfRooms, int noOfChildrens, int noOfAdults,
                                   String status, LocalDateTime checkInDate, LocalDateTime checkOutDate, double totalFare) {
        this.reservationId = reservationId;
        this.roomId = roomId;
        this.noOfRooms = noOfRooms;
        this.noOfChildrens = noOfChildrens;
        this.noOfAdults = noOfAdults;
        this.status = status;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalFare = totalFare;
    }

    // Getters and setters

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public int getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(int noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public int getNoOfChildrens() {
        return noOfChildrens;
    }

    public void setNoOfChildrens(int noOfChildrens) {
        this.noOfChildrens = noOfChildrens;
    }

    public int getNoOfAdults() {
        return noOfAdults;
    }

    public void setNoOfAdults(int noOfAdults) {
        this.noOfAdults = noOfAdults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(double totalFare) {
        this.totalFare = totalFare;
    }
}
