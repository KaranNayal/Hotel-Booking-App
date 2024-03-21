package com.hexaware.CozyHeavenStay.controller;

import com.hexaware.CozyHeavenStay.dto.HotelDTO;
import com.hexaware.CozyHeavenStay.dto.HotelOwnerDTO;
import com.hexaware.CozyHeavenStay.dto.HotelRequestDTO;
import com.hexaware.CozyHeavenStay.dto.ReviewDTO;
import com.hexaware.CozyHeavenStay.dto.RoomDTO;
import com.hexaware.CozyHeavenStay.service.HotelOwnerService;
import com.hexaware.CozyHeavenStay.service.HotelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService hotelService;
    private final HotelOwnerService hotelOwnerService;

    @Autowired
    public HotelController(HotelService hotelService, HotelOwnerService hotelOwnerService) {
        this.hotelService = hotelService;
        this.hotelOwnerService = hotelOwnerService;
    }

//    @PostMapping("/add")
//    public ResponseEntity<HotelDTO> addHotel(@RequestBody HotelDTO hotelDTO) {
//        HotelDTO addedHotel = hotelService.addHotel(hotelDTO);
//        return new ResponseEntity<>(addedHotel, HttpStatus.CREATED);
//    }

 
//    @PostMapping("/add")
//    public ResponseEntity<HotelDTO> addHotel(@RequestBody HotelRequestDTO requestDTO) {
//        String ownerUsername = requestDTO.getOwnerDTO().getUserName(); // Extract ownerUsername
//        Long ownerId = requestDTO.getOwnerDTO().getOwnerId(); // Extract ownerId
//        requestDTO.getHotelDTO().setOwnerId(ownerId); // Set ownerId in hotelDTO
//        // Call the service method to add the hotel
//        HotelDTO addedHotelDTO = hotelService.addHotel(requestDTO.getHotelDTO());
//
//        // Return the added hotel and HTTP status
//        return new ResponseEntity<>(addedHotelDTO, HttpStatus.CREATED);
//    }
    
 // Modify your controller method to accept HotelRequestDTO
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_OWNER')")	
    public ResponseEntity<HotelDTO> addHotel(@RequestBody HotelRequestDTO requestDTO) {
        String ownerUsername = requestDTO.getOwnerDTO().getUserName(); // Extract ownerUsername
        Long ownerId = requestDTO.getOwnerDTO().getOwnerId(); // Extract ownerId
        requestDTO.getHotelDTO().setOwnerId(ownerId); // Set ownerId in hotelDTO
        
        // Construct location from address, city, state, and country
        String location = String.format("%s, %s, %s, %s", 
                                        requestDTO.getHotelDTO().getAddress(),
                                        requestDTO.getHotelDTO().getCity(),
                                        requestDTO.getHotelDTO().getState(),
                                        requestDTO.getHotelDTO().getCountry());
        requestDTO.getHotelDTO().setLocation(location); // Set location in hotelDTO
        
        // Call the service method to add the hotel
        HotelDTO addedHotelDTO = hotelService.addHotel(requestDTO.getHotelDTO());

        // Return the added hotel and HTTP status
        return new ResponseEntity<>(addedHotelDTO, HttpStatus.CREATED);
    }

      
    @GetMapping("/gethotelbyid/{id}")
   @PreAuthorize("hasAuthority('ROLE_OWNER')")
    public HotelDTO getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

    @GetMapping("/getallhotels")
    //@PreAuthorize("hasAuthority('ROLE_OWNER')")
    public List<HotelDTO> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @PutMapping("/updatehotel/{id}")
   // @PreAuthorize("hasAuthority('ROLE_OWNER')")
    public HotelDTO updateHotel(@PathVariable Long id, @RequestBody HotelDTO hotelDTO) {
        return hotelService.updateHotel(id, hotelDTO);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_OWNER')")
    public void deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
    }

    @GetMapping("/getrooms/{hotelId}/rooms")
    public List<RoomDTO> getHotelRooms(@PathVariable Long hotelId) {
        return hotelService.getHotelRooms(hotelId);
    }

    @GetMapping("/getreviews/{hotelId}/reviews")
    public List<ReviewDTO> getHotelReviews(@PathVariable Long hotelId) {
        return hotelService.getHotelReviews(hotelId);
    }

    @GetMapping("/getlocation/{city}")
    public List<HotelDTO> getHotelsByLocation(@PathVariable String city) {
        // Update the location parameter to accept any word using a wildcard
        String wildcardLocation = city;
        return hotelService.getHotelsByLocation(wildcardLocation);
    }


    @GetMapping("/getamenities/{amenities}")
    public List<HotelDTO> getHotelsByAmenities(@PathVariable String amenities) {
        return hotelService.getHotelsByAmenities(amenities);
    }

    @GetMapping("/getroomtype/{roomType}")
    public List<HotelDTO> getHotelsWithRoomType(@PathVariable String roomType) {
        return hotelService.getHotelsWithRoomType(roomType);
    }
    
    @GetMapping("/gethotelsbyowner/{ownerId}")
    public List<HotelDTO> getHotelsByOwnerId(@PathVariable Long ownerId) {
        return hotelService.getHotelsByOwnerId(ownerId);
    }
}
