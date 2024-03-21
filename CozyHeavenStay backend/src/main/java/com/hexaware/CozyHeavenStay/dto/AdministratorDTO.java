package com.hexaware.CozyHeavenStay.dto;

import java.util.List;
import java.util.Set;

import com.hexaware.CozyHeavenStay.entities.Role;

public class AdministratorDTO {
    private Long adminId;
    private String userName;
    private String password;
    private String email;
    private Set<Role> roles;
    
    // Constructors, getters, and setters
    public AdministratorDTO() {
        super();
    }

    public AdministratorDTO(Long adminId, String userName, String password, String email,Set<Role> roles) {
		super();
		this.adminId = adminId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.roles = roles;
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
	
    public Set<Role> getRoles() {
	   return roles;
	}

    public void setRoles(Set<Role> roles) {
	    this.roles = roles;
	}

	@Override
	public String toString() {
		return "AdministratorDTO [adminId=" + adminId + ", userName=" + userName + ", password=" + password + ", email="
				+ email + ", roles=" + roles + "]";
	}
}
