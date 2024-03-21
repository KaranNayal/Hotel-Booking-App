package com.hexaware.CozyHeavenStay.entities;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	 @NotBlank(message = "Email is required")
	    @Email(message = "Invalid email format")
	    private String email;

	    @NotBlank(message = "Username is required")
	    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
	    private String userName;

	    @NotBlank(message = "Gender is required")
	    private String gender;

	    @NotBlank(message = "Password is required")
	    @Size(min = 6, message = "Password must be at least 6 characters")
	    private String password;

	    @NotBlank(message = "Address is required")
	    private String address;

	    @Pattern(regexp = "\\d{10}", message = "Contact number must be 10 digits")
	    private String contactNo;

	    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
		@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
		private Set<Role> roles;
	    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;

    
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
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
		return "User [userId=" + userId + ", email=" + email + ", userName=" + userName + ", gender=" + gender
				+ ", password=" + password + ", address=" + address + ", contactNo=" + contactNo + ", roles=" + roles
				+ ", reservations=" + reservations + ", reviews=" + reviews + "]";
	}
	
	
	
	
}
