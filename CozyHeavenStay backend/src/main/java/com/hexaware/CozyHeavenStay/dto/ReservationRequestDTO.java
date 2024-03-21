package com.hexaware.CozyHeavenStay.dto;

import java.time.LocalDateTime;

import com.hexaware.CozyHeavenStay.entities.Room;
import com.hexaware.CozyHeavenStay.entities.Room.RoomType;

public class ReservationRequestDTO {
    private Long roomId;
    private Long userId;
    private Long hotelId;
    private Room.RoomType roomType;
    private int noOfRooms;
    private int noOfChildren;
    private int noOfAdults;
    private String bedType;
    private boolean acStatus;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private int maxOccupancy; // Add maxOccupancy field
    private double totalFare;
	public ReservationRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public ReservationRequestDTO(Long roomId, Long userId, Long hotelId, RoomType roomType, int noOfRooms,
			int noOfChildren, int noOfAdults, String bedType, boolean acStatus, LocalDateTime checkInDate,
			LocalDateTime checkOutDate, int maxOccupancy, double totalFare) {
		super();
		this.roomId = roomId;
		this.userId = userId;
		this.hotelId = hotelId;
		this.roomType = roomType;
		this.noOfRooms = noOfRooms;
		this.noOfChildren = noOfChildren;
		this.noOfAdults = noOfAdults;
		this.bedType = bedType;
		this.acStatus = acStatus;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.maxOccupancy = maxOccupancy;
		this.totalFare = totalFare;
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
	public int getNoOfChildren() {
		return noOfChildren;
	}
	public void setNoOfChildren(int noOfChildren) {
		this.noOfChildren = noOfChildren;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getHotelId() {
		return hotelId;
	}
	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}
	
	public Room.RoomType getRoomType() {
		return roomType;
	}
	public void setRoomType(Room.RoomType roomType) {
		this.roomType = roomType;
	}

	public int getMaxOccupancy() {
		return maxOccupancy;
	}
	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
	public double getTotalFare() {
		return totalFare;
	}
	public void setTotalFare(double totalFare) {
		this.totalFare = totalFare;
	}
	public String getBedType() {
		return bedType;
	}
	public void setBedType(String bedType) {
		this.bedType = bedType;
	}
	public boolean isAcStatus() {
		return acStatus;
	}
	public void setAcStatus(boolean acStatus) {
		this.acStatus = acStatus;
	}



	@Override
	public String toString() {
		return "ReservationRequestDTO [roomId=" + roomId + ", userId=" + userId + ", hotelId=" + hotelId + ", roomType="
				+ roomType + ", noOfRooms=" + noOfRooms + ", noOfChildren=" + noOfChildren + ", noOfAdults="
				+ noOfAdults + ", bedType=" + bedType + ", acStatus=" + acStatus + ", checkInDate=" + checkInDate
				+ ", checkOutDate=" + checkOutDate + ", maxOccupancy=" + maxOccupancy + ", totalFare=" + totalFare
				+ "]";
	}


}
