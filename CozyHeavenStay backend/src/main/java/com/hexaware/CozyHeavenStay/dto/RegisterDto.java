package com.hexaware.CozyHeavenStay.dto;

import java.util.Set;

import com.hexaware.CozyHeavenStay.entities.Role;

public class RegisterDto {
	private String userName;
	private String email;
	private String password;
    private String gender;
	private String address;
	private String contactNo;
    private Long hotelId; 
    private Long ownerId; 
    private Set<Role> role;
	
	public RegisterDto() {
		super();
		// TODO Auto-generated constructor stub
	}



	public RegisterDto(String userName, String email, String password, String gender, String address, String contactNo,
			Long hotelId, Long ownerId, Set<Role> role) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.address = address;
		this.contactNo = contactNo;
		this.hotelId = hotelId;
		this.ownerId = ownerId;
		this.role = role;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getContactNo() {
		return contactNo;
	}



	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}



	public Long getHotelId() {
		return hotelId;
	}



	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}



	public Long getOwnerId() {
		return ownerId;
	}



	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}



	public Set<Role> getRole() {
		return role;
	}



	public void setRole(Set<Role> role) {
		this.role = role;
	}



	@Override
	public String toString() {
		return "RegisterDto [userName=" + userName + ", email=" + email + ", password=" + password + ", gender="
				+ gender + ", address=" + address + ", contactNo=" + contactNo + ", hotelId=" + hotelId + ", ownerId="
				+ ownerId + ", role=" + role + "]";
	}
	
}