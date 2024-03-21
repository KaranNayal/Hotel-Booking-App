package com.hexaware.CozyHeavenStay.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.CozyHeavenStay.dto.ReservationDTO;
import com.hexaware.CozyHeavenStay.dto.ReservationRequestDTO;
import com.hexaware.CozyHeavenStay.dto.RoomDTO;
import com.hexaware.CozyHeavenStay.entities.Room;
import com.hexaware.CozyHeavenStay.service.HotelService;
import com.hexaware.CozyHeavenStay.service.RoomService;
import com.hexaware.CozyHeavenStay.repository.RoomRepository;


import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

	@Autowired
    private final RoomService roomService;
	private final RoomRepository roomRepository;
    
    @Autowired
    private HotelService hotelService;

    @Autowired
    public RoomController(RoomService roomService,RoomRepository roomRepository) {
        this.roomService = roomService;
        this.roomRepository = roomRepository;
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ROLE_OWNER')")	
    public ResponseEntity<?> addRoom(@RequestBody RoomDTO roomRequestDTO) {
        // Convert RoomRequestDTO to RoomDTO
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomNumber(roomRequestDTO.getRoomNumber());
        roomDTO.setBedType(roomRequestDTO.getBedType());
        roomDTO.setAvailability(roomRequestDTO.getAvailability());
        roomDTO.setMaxOccupancy(roomRequestDTO.getMaxOccupancy());
        roomDTO.setAcStatus(roomRequestDTO.isAcStatus());
        roomDTO.setBaseFare(roomRequestDTO.getBaseFare());
        roomDTO.setRoomType(roomRequestDTO.getRoomType());
        roomDTO.setHotelId(roomRequestDTO.getHotelId());

        // Call service method to add room
        RoomDTO savedRoomDTO = roomService.saveRoom(roomDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedRoomDTO);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable Long id) {
        try {
            RoomDTO roomDTO = roomService.getRoomById(id);
            if (roomDTO != null) {
                // Check if the roomDTO's hotelId is null, and populate it if necessary
                if (roomDTO.getHotelId() == null) {
                    // Fetch the room entity from the database using RoomRepository instance
                    Room room = roomRepository.findById(id).orElse(null);
                    if (room != null && room.getHotel() != null) {
                        roomDTO.setHotelId(room.getHotel().getHotelId());
                    } else {
                        // Handle the case where the room or its associated hotel is not found
                        return ResponseEntity.notFound().build();
                    }
                }
                return ResponseEntity.ok(roomDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching room by id");
        }
    }



    @GetMapping("/lastAdded/{hotelId}")
    public ResponseEntity<String> getLastAddedRoomNumber(@PathVariable Long hotelId) {
        String lastRoomNumber = roomService.getLastAddedRoomNumber(hotelId);
        if (lastRoomNumber != null) {
            return new ResponseEntity<>(lastRoomNumber, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    @GetMapping("/getallrooms")
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        List<RoomDTO> roomDTOs = roomService.getAllRooms();
        // Perform null check for hotel before accessing hotelId
        for (RoomDTO roomDTO : roomDTOs) {
            // Check if the hotel ID is not null
            if (roomDTO.getHotelId() != null) {
                // No need to call getHotelId() again, set directly
                roomDTO.setHotelId(roomDTO.getHotelId());
            }
        }
        return new ResponseEntity<>(roomDTOs, HttpStatus.OK);
    }



    @GetMapping("/getbyhotelid/{hotelId}")
    public List<RoomDTO> getRoomsByHotelId(@PathVariable Long hotelId) {
        return roomService.getRoomsByHotelId(hotelId);
    }

    @GetMapping("/number/{roomNumber}/hotel/{hotelId}")
    public ResponseEntity<?> getRoomByRoomNumberAndHotelId(@PathVariable String roomNumber, @PathVariable Long hotelId) {
        RoomDTO roomDTO = roomService.getRoomByRoomNumberAndHotelId(roomNumber, hotelId);
        if (roomDTO != null) {
            return ResponseEntity.ok(roomDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found with room number: " + roomNumber + " and hotel ID: " + hotelId);
        }
    }
    
    
    @GetMapping("/getroombytype/{type}")
    public List<RoomDTO> getRoomsByType(@PathVariable Room.RoomType type) {
        return roomService.getRoomsByType(type);
    }

    @GetMapping("/maxoccupancygreaterthan/{minOccupancy}")
    public List<RoomDTO> getRoomsByMaxOccupancyGreaterThanEqual(@PathVariable int minOccupancy) {
        return roomService.getRoomsByMaxOccupancyGreaterThanEqual(minOccupancy);
    }

    @GetMapping("/acStatus/{acStatus}")
    public List<RoomDTO> getRoomsByAcStatus(@PathVariable boolean acStatus) {
        return roomService.getRoomsByAcStatus(acStatus);
    }

    @GetMapping("/bedType/{bedType}")
    public List<RoomDTO> getRoomsByBedType(@PathVariable String bedType) {
        return roomService.getRoomsByBedType(bedType);
    }

    @GetMapping("/maxBaseFare/{maxBaseFare}")
    public List<RoomDTO> getRoomsByBaseFareLessThan(@PathVariable double maxBaseFare) {
        return roomService.getRoomsByBaseFareLessThan(maxBaseFare);
    }

    @GetMapping("/baseFareBetween/{minBaseFare}/{maxBaseFare}")
    public List<RoomDTO> getRoomsByBaseFareBetween(@PathVariable double minBaseFare, @PathVariable double maxBaseFare) {
        return roomService.getRoomsByBaseFareBetween(minBaseFare, maxBaseFare);
    }


    @GetMapping("/calculateTotalFare")
    public double calculateTotalFare(
            @RequestParam Room.RoomType roomType,
            @RequestParam LocalDateTime checkInDate,
            @RequestParam LocalDateTime checkOutDate,
            @RequestParam int noOfRooms) {
        return roomService.calculateTotalFare(roomType, checkInDate, checkOutDate, noOfRooms);
    }
    
    
    @PutMapping("/update/{roomId}")
    public ResponseEntity<?> updateRoom(@PathVariable Long roomId, @RequestBody RoomDTO roomRequestDTO) {
        RoomDTO updatedRoomDTO = roomService.updateRoom(roomId, roomRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedRoomDTO);
    }

    @DeleteMapping("/delete/{roomId}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long roomId) {
        roomService.deleteRoom(roomId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    
    
}
