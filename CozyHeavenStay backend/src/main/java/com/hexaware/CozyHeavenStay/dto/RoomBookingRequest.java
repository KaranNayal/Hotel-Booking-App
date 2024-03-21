package com.hexaware.CozyHeavenStay.dto;

import java.time.LocalDateTime;

public class RoomBookingRequest {
    private Long userId;
    private Long roomId;
    private int noOfRooms;
    private int noOfChildrens;
    private int noOfAdults;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
	
    public RoomBookingRequest() {
		super();
	}

	public RoomBookingRequest(Long userId, Long roomId, int noOfRooms, int noOfChildrens, int noOfAdults,
			LocalDateTime checkInDate, LocalDateTime checkOutDate) {
		super();
		this.userId = userId;
		this.roomId = roomId;
		this.noOfRooms = noOfRooms;
		this.noOfChildrens = noOfChildrens;
		this.noOfAdults = noOfAdults;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
    
    
}
