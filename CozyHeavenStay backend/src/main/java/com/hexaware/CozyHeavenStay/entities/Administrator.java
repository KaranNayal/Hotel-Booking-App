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
public class Administrator {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long adminId;
	
	@NotBlank(message = "Username is required")
    private String userName;
    
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "admin_roles", joinColumns = @JoinColumn(name = "admin_id", referencedColumnName = "adminId"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles;
    
	public Administrator() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Administrator(Long adminId, @NotBlank(message = "Username is required") String userName,
			@NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be at least 8 characters long") String password,
			@NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email) {
		super();
		this.adminId = adminId;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
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

	@Override
	public String toString() {
		return "Administrator [adminId=" + adminId + ", userName=" + userName + ", password=" + password + ", email="
				+ email + "]";
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
		
}
}

