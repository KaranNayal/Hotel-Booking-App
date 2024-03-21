package com.hexaware.CozyHeavenStay.dto;
public class LoginDto {
	
	private String userName;
	private String password;
	public LoginDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginDto(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
		
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
	
	@Override
	public String toString() {
		return "LoginDto [ userName=" + userName + ", password=" + password + "]";
	}
	
	
	
}