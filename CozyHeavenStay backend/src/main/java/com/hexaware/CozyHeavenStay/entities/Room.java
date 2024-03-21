package com.hexaware.CozyHeavenStay.entities;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long roomId;
    @NotBlank(message = "Room number is required")
    private String roomNumber;
    
    //@NotBlank(message = "Bed type is required")
    private String bedType;
    
    @Enumerated(EnumType.STRING)
    private RoomAvailability availability;
    
    @Min(value = 1, message = "Maximum occupancy should be at least 1")
    private int maxOccupancy;
    
    private boolean acStatus;
    
    @Min(value = 0, message = "Base fare cannot be negative")
    private double baseFare;
    
    @NotNull(message = "Room type is required")
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @ManyToOne
    @JsonBackReference 
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    
    @ManyToMany(mappedBy = "rooms", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public Room() {
        super();
    }


    
    public Room(Long roomId, @NotBlank(message = "Room number is required") String roomNumber, String bedType,
			@NotBlank(message = "Availability status is required") RoomAvailability availability,
			@Min(value = 1, message = "Maximum occupancy should be at least 1") int maxOccupancy, boolean acStatus,
			@Min(value = 0, message = "Base fare cannot be negative") double baseFare,
			@NotNull(message = "Room type is required") RoomType roomType, Hotel hotel,
			List<Reservation> reservations) {
		super();
		this.roomId = roomId;
		this.roomNumber = roomNumber;
		this.bedType = bedType;
		this.availability = availability;
		this.maxOccupancy = maxOccupancy;
		this.acStatus = acStatus;
		this.baseFare = baseFare;
		this.roomType = roomType;
		this.hotel = hotel;
		this.reservations = reservations;
	}



	// Getters and setters

    @Override
    public String toString() {
        return "Room [roomId=" + roomId + ", roomNumber=" + roomNumber + ", bedType=" + bedType + ", availability="
                + availability + ", maxOccupancy=" + maxOccupancy + ", acStatus=" + acStatus + ", baseFare=" + baseFare
                + ", roomType=" + roomType + ", hotel=" + hotel + "]";
    }
    
    // Other methods...

    public enum RoomType {
        STANDARD("Single Bed Room", 2000),
        DELUXE("Double Bed Room", 4500),
        SUITE("King Bed Room", 7500);

        private final String displayName;
        private final int price;

        RoomType(String displayName, int price) {
            this.displayName = displayName;
            this.price = price;
        }

        public String getDisplayName() {
            return displayName;
        }

        public int getPrice() {
            return price;
        }
    }
    
    public enum RoomAvailability {
        AVAILABLE,
        BOOKED,
        RESERVED,
        UNDER_MAINTENANCE
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

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
    
}