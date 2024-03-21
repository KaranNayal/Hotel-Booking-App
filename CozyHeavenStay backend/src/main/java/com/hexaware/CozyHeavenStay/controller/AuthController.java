package com.hexaware.CozyHeavenStay.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.CozyHeavenStay.dto.JWTAuthResponse;
import com.hexaware.CozyHeavenStay.dto.LoginDto;
import com.hexaware.CozyHeavenStay.dto.RegisterDto;
import com.hexaware.CozyHeavenStay.service.AuthService;



@RestController
@RequestMapping("/api/authenticate")
@CrossOrigin("http://localhost:3000")
public class AuthController {
	private AuthService authService;
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	@PostMapping(value = {"/login","/signin"})
	public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto dto)
	{
		JWTAuthResponse token = authService.login(dto);
		/*JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
		jwtAuthResponse.setAccessToken(token);*/
		return ResponseEntity.ok(token);
	}

 
	@PostMapping(value = {"/register","/signup"})
	public ResponseEntity<String> register(@RequestBody RegisterDto dto)
	{
		String value = authService.register(dto);
		return new ResponseEntity<>(value, HttpStatus.CREATED);
	}
	
	
	  @PostMapping("/registerowner")
	 @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	    public ResponseEntity<String> registerOwner(@RequestBody RegisterDto dto) {
	        String value = authService.registerOwner(dto);
	        return new ResponseEntity<>(value, HttpStatus.CREATED);
	    }

	    @PostMapping("/registeradmin")
	    @PreAuthorize("hasAuthority('ROLE_ADMIN')")  
	    public ResponseEntity<String> registerAdmin(@RequestBody RegisterDto dto) {
	        String value = authService.registerAdmin(dto);
	        return new ResponseEntity<>(value, HttpStatus.CREATED);
	    }
	    
	    @GetMapping("/fetchIds")
	    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER', 'ROLE_USER')")
	    public ResponseEntity<Map<String, Long>> fetchIds(Authentication authentication) {
	        Map<String, Long> ids = new HashMap<>();
	        String role = authentication.getAuthorities().iterator().next().getAuthority();
	        Long id;
	        if (role.equals("ROLE_ADMIN")) {
	            id = authService.getAdminId(authentication.getName());
	            ids.put("adminId", id);
	        } else if (role.equals("ROLE_OWNER")) {
	            id = authService.getOwnerId(authentication.getName());
	            ids.put("ownerId", id);
	        } else if (role.equals("ROLE_USER")) {
	            id = authService.getUserId(authentication.getName());
	            ids.put("userId", id);
	        }
	        return ResponseEntity.ok(ids);
	    }

	
}