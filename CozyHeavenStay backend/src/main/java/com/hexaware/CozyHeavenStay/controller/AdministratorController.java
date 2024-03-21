package com.hexaware.CozyHeavenStay.controller;

import com.hexaware.CozyHeavenStay.dto.AdministratorDTO;
import com.hexaware.CozyHeavenStay.dto.ReservationDTO;
import com.hexaware.CozyHeavenStay.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin")
public class AdministratorController {

    private final AdministratorService administratorService;

    @Autowired
    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }


    @GetMapping("/admin/{id}")
   // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
     public ResponseEntity<AdministratorDTO> getAdministratorById(@PathVariable Long id) {
        AdministratorDTO administratorDTO = administratorService.getAdministratorById(id);
        return ResponseEntity.ok(administratorDTO);
    }

    @PutMapping("/updateadmin/{id}")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<AdministratorDTO> updateAdministrator(@PathVariable Long id, @RequestBody AdministratorDTO administratorDTO) {
        AdministratorDTO updatedAdministrator = administratorService.updateAdministrator(id, administratorDTO);
        return ResponseEntity.ok(updatedAdministrator);
    }

    @DeleteMapping("/deleteadmin/{id}")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')") 
    public ResponseEntity<Void> deleteAdministrator(@PathVariable Long id) {
        administratorService.deleteAdministrator(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/getalladmin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<AdministratorDTO>> getAllAdmins() {
        List<AdministratorDTO> admins = administratorService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }
    

    @GetMapping("/allbookings")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<ReservationDTO>> getAllBookings() {
        List<ReservationDTO> bookings = administratorService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }
    
    @DeleteMapping("/admin/bookings/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long bookingId) {
        administratorService.deleteBooking(bookingId);
        return ResponseEntity.status(HttpStatus.OK).body("Booking with ID " + bookingId + " has been deleted successfully.");
    }

}
