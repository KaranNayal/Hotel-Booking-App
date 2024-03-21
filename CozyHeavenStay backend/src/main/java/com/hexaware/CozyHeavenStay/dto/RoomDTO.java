package com.hexaware.CozyHeavenStay.dto;

import com.hexaware.CozyHeavenStay.entities.Room.RoomAvailability;
import com.hexaware.CozyHeavenStay.entities.Room.RoomType;

public class RoomDTO {
    private Long roomId;
    private String roomNumber;
    private String bedType;
    private RoomAvailability availability;
    private int maxOccupancy;
    private boolean acStatus;
    private double baseFare;
    private RoomType roomType;
    private Long hotelId; // Added hotelId field to match the backend service requirements

    // Constructors, getters, and setters
    public RoomDTO() {
        super();
    }
  
    public RoomDTO(Long roomId, String roomNumber, String bedType, RoomAvailability availability, int maxOccupancy,
			boolean acStatus, double baseFare, RoomType roomType, Long hotelId) {
		super();
		this.roomId = roomId;
		this.roomNumber = roomNumber;
		this.bedType = bedType;
		this.availability = availability;
		this.maxOccupancy = maxOccupancy;
		this.acStatus = acStatus;
		this.baseFare = baseFare;
		this.roomType = roomType;
		this.hotelId = hotelId;
	}


	public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public RoomAvailability getAvailability() {
		return availability;
	}

	public void setAvailability(RoomAvailability availability) {
		this.availability = availability;
	}

	public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public boolean isAcStatus() {
        return acStatus;
    }

    public void setAcStatus(boolean acStatus) {
        this.acStatus = acStatus;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(double baseFare) {
        this.baseFare = baseFare;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }
}
