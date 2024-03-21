package com.hexaware.CozyHeavenStay.controller;

import com.hexaware.CozyHeavenStay.dto.HotelOwnerDTO;
import com.hexaware.CozyHeavenStay.dto.ReservationDTO;
import com.hexaware.CozyHeavenStay.dto.RoomDTO;
import com.hexaware.CozyHeavenStay.service.HotelOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/hotel-owners")
public class HotelOwnerController {

    private final HotelOwnerService hotelOwnerService;

    @Autowired
    public HotelOwnerController(HotelOwnerService hotelOwnerService) {
        this.hotelOwnerService = hotelOwnerService;
    }
    @GetMapping("/getowner/{id}")
   // @PreAuthorize("hasAuthority('ROLE_OWNER')or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<HotelOwnerDTO> getHotelOwnerById(@PathVariable Long id) {
        return ResponseEntity.ok(hotelOwnerService.getHotelOwnerById(id));
    }

    @PutMapping("/updateowner/{id}")
    @PreAuthorize("hasAuthority('ROLE_OWNER')")
    public ResponseEntity<HotelOwnerDTO> updateHotelOwner(@PathVariable Long id, @RequestBody HotelOwnerDTO hotelOwnerDTO) {
        return ResponseEntity.ok(hotelOwnerService.updateHotelOwner(id, hotelOwnerDTO));
    }

    @DeleteMapping("/deleteowner/{id}")
    //@PreAuthorize("hasAuthority('ROLE_OWNER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteHotelOwner(@PathVariable Long id) {
        hotelOwnerService.deleteHotelOwner(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getallowner")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<HotelOwnerDTO>> getAllHotelOwners() {
        List<HotelOwnerDTO> hotelOwnerDTOs = hotelOwnerService.getAllHotelOwners();
        return ResponseEntity.ok(hotelOwnerDTOs);
    }
    
}
