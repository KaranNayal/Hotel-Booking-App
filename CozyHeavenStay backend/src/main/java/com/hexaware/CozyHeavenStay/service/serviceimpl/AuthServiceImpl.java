package com.hexaware.CozyHeavenStay.service.serviceimpl;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.CozyHeavenStay.customexception.BadRequestException;
import com.hexaware.CozyHeavenStay.dto.AdministratorDTO;
import com.hexaware.CozyHeavenStay.dto.HotelOwnerDTO;
import com.hexaware.CozyHeavenStay.dto.JWTAuthResponse;
import com.hexaware.CozyHeavenStay.dto.LoginDto;
import com.hexaware.CozyHeavenStay.dto.RegisterDto;
import com.hexaware.CozyHeavenStay.dto.UserDTO;
import com.hexaware.CozyHeavenStay.entities.Administrator;
import com.hexaware.CozyHeavenStay.entities.HotelOwner;
import com.hexaware.CozyHeavenStay.entities.Role;
import com.hexaware.CozyHeavenStay.entities.User;
import com.hexaware.CozyHeavenStay.repository.AdministratorRepository;
import com.hexaware.CozyHeavenStay.repository.HotelOwnerRepository;
import com.hexaware.CozyHeavenStay.repository.RoleRepository;
import com.hexaware.CozyHeavenStay.repository.UserRepository;
import com.hexaware.CozyHeavenStay.security.JwtTokenProvider;
import com.hexaware.CozyHeavenStay.service.AdministratorService;
import com.hexaware.CozyHeavenStay.service.AuthService;
import com.hexaware.CozyHeavenStay.service.HotelOwnerService;
import com.hexaware.CozyHeavenStay.service.UserService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

	
	private AuthenticationManager authenticationManager;
	private UserRepository userRepo;
	private RoleRepository roleRepo;
	private PasswordEncoder passwordEncoder;
	private JwtTokenProvider jwtTokenProvider;
	private final AdministratorRepository adminRepo;
	private final HotelOwnerRepository ownerRepo;
	private UserService userService;
	private HotelOwnerService hotelOwnerService;
	private AdministratorService administratorService;
	
	@Autowired
	public AuthServiceImpl(AuthenticationManager authenticationManager, 
			UserRepository userRepo, RoleRepository roleRepo,PasswordEncoder passwordEncoder,
			JwtTokenProvider jwtTokenProvider, AdministratorRepository adminRepo, 
			HotelOwnerRepository ownerRepo, UserService userService, HotelOwnerService hotelOwnerService,
			AdministratorService administratorService) {
		this.authenticationManager = authenticationManager;
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
		this.adminRepo = adminRepo; // Initialize the AdministratorRepository
		this.ownerRepo = ownerRepo; // Initialize the AdministratorRepository
        this.userService = userService;
        this.hotelOwnerService = hotelOwnerService;
        this.administratorService = administratorService;
		
	}
	@Override
	public JWTAuthResponse login(LoginDto dto) {
	    Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword()));
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    String token = jwtTokenProvider.generateToken(authentication);
	    
	    // Fetch user from the administrator table
	    Administrator admin = adminRepo.findByUserName(dto.getUserName()).orElse(null);
	    
	    // Fetch user from the hotel_owner table
	    HotelOwner owner = ownerRepo.findByUserName(dto.getUserName()).orElse(null);
	    
	    UserDTO userDto = new UserDTO();
	    if (admin != null) {
	        AdministratorDTO adminDto = new AdministratorDTO();
	        adminDto.setAdminId(admin.getAdminId());
	        adminDto.setUserName(admin.getUserName());
	        adminDto.setEmail(admin.getEmail());
	        adminDto.setPassword(admin.getPassword());
	        adminDto.setRoles(admin.getRoles());
	       
	        // Return JWTAuthResponse with AdministratorDTO
	        return new JWTAuthResponse(token, adminDto);
	    
	    } else if (owner != null) {
	        HotelOwnerDTO ownerDto = new HotelOwnerDTO();
	        ownerDto.setHotelId(owner.getHotelId());
	        ownerDto.setOwnerId(owner.getOwnerId());
	        ownerDto.setUserName(owner.getUserName());
	        ownerDto.setEmail(owner.getEmail());
	        ownerDto.setPassword(owner.getPassword());
	        ownerDto.setRoles(owner.getRoles());
	        
	        // Return JWTAuthResponse with HotelOwnerDTO
	        return new JWTAuthResponse(token, ownerDto);
	    } else {
	        // Fetch user from the user table
	        User user = userRepo.findByUserName(dto.getUserName()).orElse(null);
	        
	        if (user != null) {
	            userDto.setUserId(user.getUserId());
	            userDto.setUserName(user.getUserName());
	            userDto.setEmail(user.getEmail());
	            userDto.setAddress(user.getAddress());
	            userDto.setContactNo(user.getContactNo());
	            userDto.setGender(user.getGender());
	            // Set other properties for the user DTO
	            
	            Set<Role> roles = user.getRoles();
	            userDto.setRoles(roles);
	        }
	        
	        // Return JWTAuthResponse with UserDTO
	        return new JWTAuthResponse(token, userDto);
	    }
	}

	
	@Override
	@Transactional
	public String register(RegisterDto dto) {
	    if (userRepo.existsByUserName(dto.getUserName())) {
	        throw new BadRequestException(HttpStatus.BAD_REQUEST, "Username already exists");
	    }
	    if (userRepo.existsByEmail(dto.getEmail())) {
	        throw new BadRequestException(HttpStatus.BAD_REQUEST, "Email already exists");
	    }
	    if (userRepo.existsByContactNo(dto.getContactNo())) {
	        throw new BadRequestException(HttpStatus.BAD_REQUEST, "Contact number already exists");
	    }
	    User user = new User();
	    user.setUserName(dto.getUserName());
	    user.setEmail(dto.getEmail());
	    user.setAddress(dto.getAddress());
	    user.setContactNo(dto.getContactNo());
	    user.setGender(dto.getGender());
	    user.setPassword(passwordEncoder.encode(dto.getPassword()));

	    // Set default role to ROLE_USER
	    Role defaultRole = roleRepo.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("Default role ROLE_USER not found"));
	    user.setRoles(Set.of(defaultRole)); // Set the roles for the user

	    userRepo.save(user);
	    
	    // Debug logging
	    logger.info("User registered successfully with default role: ROLE_USER");

	    return "Registration Successful!";
	}

	 @Override
	    public String registerAdmin(RegisterDto dto) {
	        // Implement registration logic for admin
	        // Check if username or email already exists
	        if (adminRepo.existsByUserName(dto.getUserName())) {
	            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Username already exists");
	        }
	        if (adminRepo.existsByEmail(dto.getEmail())) {
	            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Email already exists");
	        }
	        // Create a new administrator entity
	        Administrator admin = new Administrator();
	        admin.setUserName(dto.getUserName());
	        admin.setEmail(dto.getEmail());
	        admin.setPassword(passwordEncoder.encode(dto.getPassword()));
	        Role adminRole = roleRepo.findByName("ROLE_ADMIN").orElseThrow(() -> new RuntimeException("Admin role not found"));
		    admin.setRoles(Collections.singleton(adminRole));
	        // Save the administrator to the database
	        adminRepo.save(admin);
	        return "Admin registration successful!";
	    }

	@Override
	public String registerOwner(RegisterDto dto) {
	    // Implement registration logic for owner
	    // Check if username or email already exists
	    if (ownerRepo.existsByUserName(dto.getUserName())) {
	        throw new BadRequestException(HttpStatus.BAD_REQUEST, "Username already exists");
	    }
	    if (ownerRepo.existsByEmail(dto.getEmail())) {
	        throw new BadRequestException(HttpStatus.BAD_REQUEST, "Email already exists");
	    }
	    // Create a new user entity
	    HotelOwner owner = new HotelOwner();
	    owner.setUserName(dto.getUserName());
	    owner.setEmail(dto.getEmail());
	    owner.setHotelId(dto.getHotelId());
	    owner.setPassword(passwordEncoder.encode(dto.getPassword()));
	    // Set the owner role for the user
	    Role ownerRole = roleRepo.findByName("ROLE_OWNER").orElseThrow(() -> new RuntimeException("Owner role not found"));
	    owner.setRoles(Collections.singleton(ownerRole));
	    // Save the user to the database
	    ownerRepo.save(owner);
	    return "Owner registration successful!";
	}
	
	@Override
	public Long getUserId(String username) {
	    // Fetch user ID from the database based on the username
	    return userService.findUserIdByUsername(username);
	}

	@Override
	public Long getOwnerId(String username) {
	    // Fetch owner ID from the database based on the username
	    return hotelOwnerService.findOwnerIdByUsername(username);
	}

	@Override
	public Long getAdminId(String username) {
	    // Fetch admin ID from the database based on the username
	    return administratorService.findAdminIdByUsername(username);
	}



}