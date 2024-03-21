package com.hexaware.CozyHeavenStay.dto;

import java.util.Set;

import com.hexaware.CozyHeavenStay.entities.Role;

public class UserDTO {
    private Long userId;
    private String email;
    private String userName;
    private String gender;
    private String password;
    private String address;
    private String contactNo;
    private Set<Role> roles; // Updated field for roles


    // Constructors
    public UserDTO() {
    }

  

    public UserDTO(Long userId, String email, String userName, String gender, String password, String address,
			String contactNo, Set<Role> roles) {
		super();
		this.userId = userId;
		this.email = email;
		this.userName = userName;
		this.gender = gender;
		this.password = password;
		this.address = address;
		this.contactNo = contactNo;
		this.roles = roles;
	}



	// Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", email=" + email + ", userName=" + userName + ", gender=" + gender
				+ ", password=" + password + ", address=" + address + ", contactNo=" + contactNo + ", roles=" + roles
				+ "]";
	}
	
	

   
}

