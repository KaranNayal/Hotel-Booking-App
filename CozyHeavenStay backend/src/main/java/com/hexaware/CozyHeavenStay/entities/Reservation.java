package com.hexaware.CozyHeavenStay.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hexaware.CozyHeavenStay.entities.Room.RoomAvailability;
import com.hexaware.CozyHeavenStay.entities.Room.RoomType;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Reservation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long reservationId;
	 @NotNull(message = "Room ID is required")
	    private Long roomId;
	    
	    @Min(value = 1, message = "Number of rooms should be at least 1")
	    private int noOfRooms;
	    
	    @Min(value = 0, message = "Number of children cannot be negative")
	    private int noOfChildrens;
	    
	    @Min(value = 1, message = "Number of adults should be at least 1")
	    private int noOfAdults;
	    
	    @NotBlank(message = "Status is required")
	    private String status;
	    
	    @NotNull(message = "Check-in date is required")
	    private LocalDateTime checkInDate;
	    
	    @NotNull(message = "Check-out date is required")
	    private LocalDateTime checkOutDate;
	    
	    private double totalFare;
	
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "reservation_room",
        joinColumns = @JoinColumn(name = "reservation_id"),
        inverseJoinColumns = @JoinColumn(name = "room_id"))
    private List<Room> rooms;
   
    
	public Reservation(Long reservationId, Long roomId, int noOfRooms, int noOfChildrens, int noOfAdults, String status,
			LocalDateTime checkInDate, LocalDateTime checkOutDate, double totalFare) {
		super();
		this.reservationId = reservationId;
		this.roomId = roomId;
		this.noOfRooms = noOfRooms;
		this.noOfChildrens = noOfChildrens;
		this.noOfAdults = noOfAdults;
		this.status = status;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.totalFare = totalFare;
		this.rooms = new ArrayList<>();
	}

	public Reservation() {
	       this.rooms = new ArrayList<>(); // Initialize rooms list in the constructor
	       
	}

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


	
	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", roomId=" + roomId + ", noOfRooms=" + noOfRooms
				+ ", noOfChildrens=" + noOfChildrens + ", noOfAdults=" + noOfAdults + ", status=" + status
				+ ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + ", totalFare=" + totalFare
				+ ", user=" + user + ", rooms=" + rooms + "]";
	}

	public void setUser(User user) {
        this.user = user;
    }
	
	public void setRooms(List<Reservation> savedReservations) {
		this.rooms = new ArrayList<>();
    }

	public User getUser() {
		return user;
	}

	public List<Room> getRooms() {
		return rooms;
	}



    // Lifecycle callback to update room status when reservation is made
    @PostPersist
    public void onReservation() {
        updateRoomStatus(RoomAvailability.BOOKED);
    }

    // Lifecycle callback to update room status when reservation is updated
    @PostUpdate
    public void onUpdateReservation() {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(getCheckOutDate())) {
            updateRoomStatus(RoomAvailability.AVAILABLE);
        }
    }

    private void updateRoomStatus(RoomAvailability availability) {
        for (Room room : rooms) {
            room.setAvailability(availability);
        }
    }



}
