
package com.hexaware.CozyHeavenStay.dto;

import java.util.Set;

import com.hexaware.CozyHeavenStay.entities.Role;

public class HotelOwnerDTO {
    private Long ownerId;
    private Long hotelId;
    private String userName;
    private String password;
    private String email;
    private Set<Role> roles;

    // Constructors, getters, and setters
    public HotelOwnerDTO() {
        super();
    }

    public HotelOwnerDTO(Long ownerId, Long hotelId, String userName, String password, String email,Set<Role> roles) {
        this.ownerId = ownerId;
        this.hotelId = hotelId;
        this.userName = userName;
        this.password = password;
        this.email = email;
		this.roles = roles;
    }

    // Getters and setters for fields
    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
    public Set<Role> getRoles() {
	   return roles;
	}

    public void setRoles(Set<Role> roles) {
	    this.roles = roles;
	}

	@Override
	public String toString() {
		return "HotelOwnerDTO [ownerId=" + ownerId + ", hotelId=" + hotelId + ", userName=" + userName + ", password="
				+ password + ", email=" + email + ", roles=" + roles + "]";
	}
    
    
}
