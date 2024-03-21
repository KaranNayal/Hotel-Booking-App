package com.hexaware.CozyHeavenStay.dto;

import com.hexaware.CozyHeavenStay.entities.HotelOwner;

public class HotelDTO {
    private Long hotelId;
    private String hotelName;
    private String address;
    private String city;
    private String state;
    private String country;
    private double rating;
    private String amenities;
    private Long ownerId;
    
	public HotelDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public HotelDTO(Long hotelId, String hotelName, String address, String city, String state, String country,
			double rating, String amenities, Long ownerId) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.rating = rating;
		this.amenities = amenities;
		this.ownerId = ownerId;
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

	public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
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
	
	 public String getLocation() {
	        return address + ", " + city + ", " + state + ", " + country;
	    }
	    
	 
	 
	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	@Override
	public String toString() {
		return "HotelDTO [hotelId=" + hotelId + ", hotelName=" + hotelName + ", address=" + address + ", city=" + city
				+ ", state=" + state + ", country=" + country + ", rating=" + rating + ", amenities=" + amenities
				+ ", ownerId=" + ownerId + "]";
	}
	
}
