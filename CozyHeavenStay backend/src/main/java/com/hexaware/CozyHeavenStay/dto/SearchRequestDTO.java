package com.hexaware.CozyHeavenStay.dto;

public class SearchRequestDTO {
    private String city;
    private String checkInDate;
    private String checkOutDate;
    private int numberOfAdults;
    private int numberOfChildren;
    private int numberOfRooms;
    private String location;
    private String hotelName;

	public SearchRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchRequestDTO(String city, String checkInDate, String checkOutDate, int numberOfAdults,
			int numberOfChildren, int numberOfRooms, String location, String hotelName) {
		super();
		this.city = city;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.numberOfAdults = numberOfAdults;
		this.numberOfChildren = numberOfChildren;
		this.numberOfRooms = numberOfRooms;
		this.location = location;
		this.hotelName = hotelName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public int getNumberOfAdults() {
		return numberOfAdults;
	}

	public void setNumberOfAdults(int numberOfAdults) {
		this.numberOfAdults = numberOfAdults;
	}

	public int getNumberOfChildren() {
		return numberOfChildren;
	}

	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	@Override
	public String toString() {
		return "SearchRequestDTO [city=" + city + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate
				+ ", numberOfAdults=" + numberOfAdults + ", numberOfChildren=" + numberOfChildren + ", numberOfRooms="
				+ numberOfRooms + ", location=" + location + ", hotelName=" + hotelName + "]";
	}

}