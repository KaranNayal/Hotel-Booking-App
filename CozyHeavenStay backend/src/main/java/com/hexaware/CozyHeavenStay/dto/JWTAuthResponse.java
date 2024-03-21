package com.hexaware.CozyHeavenStay.dto;

public class JWTAuthResponse {
	private String accessToken;
	private String tokenType = "Bearer";
	private UserDTO userDto;//appending user details and JWT Token in response
	private AdministratorDTO adminDto;
	private HotelOwnerDTO ownerDto;
	
	public JWTAuthResponse() {	}
	
    public JWTAuthResponse(String accesstoken, AdministratorDTO adminDto) {
        this.accessToken = accesstoken;
        this.adminDto = adminDto;
    }
	public JWTAuthResponse(String accessToken, UserDTO userDto) {
		super();
		this.accessToken = accessToken;
		this.userDto = userDto;
	}
	public JWTAuthResponse(String accessToken, HotelOwnerDTO ownerDto) {
		super();
		this.accessToken = accessToken;
		this.ownerDto = ownerDto;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public UserDTO getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDTO userDto) {
		this.userDto = userDto;
	}
	public AdministratorDTO getAdministratorDto() {
		return adminDto;
	}
	public void setAdministratorDto(AdministratorDTO adminDto) {
		this.adminDto = adminDto;
	}
	public HotelOwnerDTO getHotelOwnerDto() {
		return ownerDto;
	}
	public void setHotelOwner(HotelOwnerDTO ownerDto) {
		this.ownerDto = ownerDto;
	}
	
}