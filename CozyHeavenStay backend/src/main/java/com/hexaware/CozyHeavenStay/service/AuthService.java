package com.hexaware.CozyHeavenStay.service;

import com.hexaware.CozyHeavenStay.dto.JWTAuthResponse;
import com.hexaware.CozyHeavenStay.dto.LoginDto;
import com.hexaware.CozyHeavenStay.dto.RegisterDto;

public interface AuthService {
	JWTAuthResponse login(LoginDto dto);
	String register(RegisterDto dto);
	String registerOwner(RegisterDto dto);
    String registerAdmin(RegisterDto dto);
    Long getUserId(String username);
    Long getOwnerId(String username);
    Long getAdminId(String username);
    
}