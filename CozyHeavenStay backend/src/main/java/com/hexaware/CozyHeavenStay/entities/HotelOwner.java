package com.hexaware.CozyHeavenStay.entities;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class HotelOwner {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ownerId;
	
    private Long hotelId;
    
    @NotBlank(message = "Username is required")
    private String userName;
   
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
	private String password;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "owner_roles", joinColumns = @JoinColumn(name = "owner_id", referencedColumnName = "ownerId"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles;
	
	@OneToMany(mappedBy = "hotelOwner", cascade = CascadeType.ALL)
    private List<Hotel> hotels;

	public HotelOwner() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public HotelOwner(Long ownerId, Long hotelId,
			@NotBlank(message = "Username is required") String userName,
			@NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be at least 8 characters long") String password,
			@NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email,
			Set<Role> roles, List<Hotel> hotels) {
		super();
		this.ownerId = ownerId;
		this.hotelId = hotelId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.roles = roles;
		this.hotels = hotels;
	}

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
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;	
    }
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "HotelOwner [ownerId=" + ownerId + ", hotelId=" + hotelId + ", userName=" + userName + ", password="
				+ password + ", email=" + email + ", roles=" + roles + ", hotels=" + hotels + "]";
	}
		
}
