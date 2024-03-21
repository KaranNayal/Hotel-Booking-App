package com.hexaware.CozyHeavenStay.dto;

import java.time.LocalDateTime;

import com.hexaware.CozyHeavenStay.entities.Room;
import com.hexaware.CozyHeavenStay.entities.Room.RoomType;

public class ReservationDTO {
    private Long reservationId;
    private Long roomId;
    private int noOfRooms;
    private int noOfChildrens;
    private String roomNumber;
    private int noOfAdults;
    private String status;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private double totalFare;
    private Long userId; 
    
    private Long hotelId;
    private Room.RoomType roomType;
    
    // Constructors
    public ReservationDTO() {
    }



    public ReservationDTO(Long reservationId, Long roomId, int noOfRooms, int noOfChildrens, String roomNumber,
			int noOfAdults, String status, LocalDateTime checkInDate, LocalDateTime checkOutDate, double totalFare,
			Long userId, Long hotelId, RoomType roomType) {
		super();
		this.reservationId = reservationId;
		this.roomId = roomId;
		this.noOfRooms = noOfRooms;
		this.noOfChildrens = noOfChildrens;
		this.roomNumber = roomNumber;
		this.noOfAdults = noOfAdults;
		this.status = status;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.totalFare = totalFare;
		this.userId = userId;
		this.hotelId = hotelId;
		this.roomType = roomType;
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
       
    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
		public String getRoomNumber() {
		return roomNumber;
	}
    public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	@Override
	public String toString() {
		return "ReservationDTO [reservationId=" + reservationId + ", roomId=" + roomId + ", noOfRooms=" + noOfRooms
				+ ", noOfChildrens=" + noOfChildrens + ", roomNumber=" + roomNumber + ", noOfAdults=" + noOfAdults
				+ ", status=" + status + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate
				+ ", totalFare=" + totalFare + ", userId=" + userId + ", hotelId=" + hotelId + ", roomType=" + roomType
				+ "]";
	}


}
