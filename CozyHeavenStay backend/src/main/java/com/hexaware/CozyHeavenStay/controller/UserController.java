package com.hexaware.CozyHeavenStay.controller;

import com.hexaware.CozyHeavenStay.dto.HotelDTO;
import com.hexaware.CozyHeavenStay.dto.ReservationDTO;
import com.hexaware.CozyHeavenStay.dto.ReservationRequestDTO;
import com.hexaware.CozyHeavenStay.dto.SearchRequestDTO;
import com.hexaware.CozyHeavenStay.dto.UserDTO;
import com.hexaware.CozyHeavenStay.service.ReservationService;
import com.hexaware.CozyHeavenStay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final ReservationService reservationService;
    

    @Autowired
    public UserController(UserService userService, ReservationService reservationService) {
        this.userService = userService;
        this.reservationService=reservationService;
    }

    
    @GetMapping("/getuserbyid/{userId}")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public UserDTO getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/getallusers")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/save")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.saveUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/update/{userId}")
    //@PreAuthorize("hasAuthority('ROLE_USER')or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(userId, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{userId}")
   @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

//    @GetMapping("/getuserbyemail/{email}")
//    //@PreAuthorize("hasAuthority('ROLE_User')")
//    public UserDTO getUserByEmail(@PathVariable String email) {
//        return userService.getUserByEmail(email);
//    }
//
//    @GetMapping("/getusersbyusername/{userName}")
//    //@PreAuthorize("hasAuthority('ROLE_User')")
//    public List<UserDTO> getUsersByUserName(@PathVariable String userName) {
//        return userService.getUsersByUserName(userName);
//    }

    @GetMapping("/{userId}/reservations")
    public ResponseEntity<List<ReservationDTO>> getUserReservations(@PathVariable Long userId) {
        List<ReservationDTO> reservations = reservationService.getUserReservations(userId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
    
    @GetMapping("/getusersbygender/{gender}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserDTO> getUsersByGender(@PathVariable String gender) {
        return userService.getUsersByGender(gender);
    }
    
    @GetMapping("/getbyusername/{userName}")
    public ResponseEntity<UserDTO> getbyUsername(@PathVariable String userName) {
        UserDTO userDTO = userService.getUserByUsername(userName);
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getusersbyaddress/{address}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserDTO> getUsersByAddress(@PathVariable String address) {
        return userService.getUsersByAddress(address);
    }

    @GetMapping("/getusersbycontactno/{contactNo}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserDTO> getUsersByContactNo(@PathVariable String contactNo) {
        return userService.getUsersByContactNo(contactNo);
    }
    
    @GetMapping("/getusersbyusernamecontaining/{keyword}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserDTO> getUsersByUserNameContaining(@PathVariable String keyword) {
        return userService.getUsersByUserNameContaining(keyword);
    }

    @PostMapping("/searchhotels")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public List<HotelDTO> searchHotels(@RequestBody SearchRequestDTO searchRequestDTO) {
        return userService.searchHotels(searchRequestDTO);
    }


    @DeleteMapping("/cancelreservation/{reservationId}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public void cancelReservation(@PathVariable Long reservationId) {
        userService.cancelReservation(reservationId);
    }
    

}
