package com.hexaware.CozyHeavenStay.entities;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hexaware.CozyHeavenStay.config.CustomDoubleSerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity

public class Hotel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long hotelId;
	 
    @NotBlank(message = "Hotel name is required")
    private String hotelName;
    
    @NotBlank(message = "Amenities are required")
    private String amenities;
    
    @NotBlank(message = "Address is required")
    private String address;
    
    @NotBlank(message = "City is required")
    private String city;
    
    @NotBlank(message = "State is required")
    private String state;
    
    @NotBlank(message = "Country is required")
    private String country;
   
    @JsonSerialize(using = CustomDoubleSerializer.class)
    private Double rating;
	
	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Room> rooms;
	
	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Review> reviews;
	
	@ManyToOne
	@JoinColumn(name = "hotel_owner_id") 
	private HotelOwner hotelOwner;
	
	@Column(name = "owner_id")
	private Long ownerId;
	
	 @NotBlank(message = "Location is required")
	    private String location;
	
	public Hotel() {
		super();
		// TODO Auto-generated constructor stub
	}	

	public Hotel(Long hotelId, @NotBlank(message = "Hotel name is required") String hotelName,
			@NotBlank(message = "Amenities are required") String amenities,
			@NotBlank(message = "Address is required") String address,
			@NotBlank(message = "City is required") String city, @NotBlank(message = "State is required") String state,
			@NotBlank(message = "Country is required") String country, Double rating, List<Room> rooms,
			List<Review> reviews, HotelOwner hotelOwner, Long ownerId,
			@NotBlank(message = "Location is required") String location) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.amenities = amenities;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.rating = rating;
		this.rooms = rooms;
		this.reviews = reviews;
		this.hotelOwner = hotelOwner;
		this.ownerId = ownerId;
		this.location = location;
	}
	public Long getHotelId() {
		return hotelId;
	}
	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getAmenities() {
		return amenities;
	}
	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	 public String getLocation() {
	        return address + ", " + city + ", " + state + ", " + country;
	    }
	
	public void setLocation(String location) {
	    // Split the location string into address, city, state, and country
	    String[] parts = location.split(", ");
	    if (parts.length == 4) {
	        this.address = parts[0];
	        this.city = parts[1];
	        this.state = parts[2];
	        this.country = parts[3];
	    } else {
	        // Handle the case where the location string is not in the expected format
	        throw new IllegalArgumentException("Invalid location format: " + location);
	    }
	}
	
	public List<Review> getReviews() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Room> getRooms() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setHotelOwner(HotelOwner hotelOwner) {
        this.hotelOwner = hotelOwner;
    }


	public Long getOwnerId() {
		return ownerId;
	}


	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}


	public HotelOwner getHotelOwner() {
		return hotelOwner;
	}

	@Override
	public String toString() {
		return "Hotel [hotelId=" + hotelId + ", hotelName=" + hotelName + ", amenities=" + amenities + ", address="
				+ address + ", city=" + city + ", state=" + state + ", country=" + country + ", rating=" + rating
				+ ", rooms=" + rooms + ", reviews=" + reviews + ", hotelOwner=" + hotelOwner + ", ownerId=" + ownerId
				+ ", location=" + location + "]";
	}


	
	
	
}

