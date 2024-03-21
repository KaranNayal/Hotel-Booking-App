package com.hexaware.CozyHeavenStay.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hexaware.CozyHeavenStay.entities.Administrator;
import com.hexaware.CozyHeavenStay.entities.HotelOwner;
import com.hexaware.CozyHeavenStay.entities.Role;
import com.hexaware.CozyHeavenStay.entities.User;
import com.hexaware.CozyHeavenStay.repository.AdministratorRepository;
import com.hexaware.CozyHeavenStay.repository.HotelOwnerRepository;
import com.hexaware.CozyHeavenStay.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private AdministratorRepository adminRepository;
    private HotelOwnerRepository ownerRepository;

    // Constructor DI
    public CustomUserDetailsService(UserRepository userRepository, 
                                     AdministratorRepository adminRepository,
                                     HotelOwnerRepository ownerRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        // Check if the username or email belongs to a user
        User user = userRepository.findByuserNameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElse(null);
        if (user != null) {
            return createUserDetails(user.getUserName(), user.getPassword(), user.getRoles());
        }

        // Check if the username or email belongs to an administrator
        Administrator admin = adminRepository.findByUserName(usernameOrEmail)
                .orElse(null);
        if (admin != null) {
            return createUserDetails(admin.getUserName(), admin.getPassword(), admin.getRoles());
        }

        // Check if the username or email belongs to a hotel owner
        HotelOwner owner = ownerRepository.findByUserName(usernameOrEmail)
                .orElse(null);
        if (owner != null) {
            return createUserDetails(owner.getUserName(), owner.getPassword(), owner.getRoles());
        }

        throw new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail);
    }

    private UserDetails createUserDetails(String username, String password, Set<Role> roles) {
        Set<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(username, password, authorities);
    }
}
