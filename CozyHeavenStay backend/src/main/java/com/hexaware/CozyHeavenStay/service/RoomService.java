package com.hexaware.CozyHeavenStay.service;

import com.hexaware.CozyHeavenStay.dto.HotelDTO;
import com.hexaware.CozyHeavenStay.dto.ReservationDTO;
import com.hexaware.CozyHeavenStay.dto.ReservationRequestDTO;
import com.hexaware.CozyHeavenStay.dto.RoomDTO;
import com.hexaware.CozyHeavenStay.dto.SearchRequestDTO;
import com.hexaware.CozyHeavenStay.entities.Room;
import com.hexaware.CozyHeavenStay.entities.Room.RoomAvailability;
import com.hexaware.CozyHeavenStay.entities.Room.RoomType;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomService {
	RoomDTO saveRoom(RoomDTO roomDTO);

    RoomDTO getRoomById(Long id);

    List<RoomDTO> getAllRooms();
    
    RoomDTO getRoomByRoomNumberAndHotelId(String roomNumber, Long hotelId);   

    List<RoomDTO> getRoomsByHotelId(Long hotelId);

    List<RoomDTO> getRoomsByType(RoomType type);

    List<RoomDTO> getRoomsByMaxOccupancyGreaterThanEqual(int minOccupancy);

    List<RoomDTO> getRoomsByAcStatus(boolean acStatus);

    List<RoomDTO> getRoomsByBedType(String bedType);

    List<RoomDTO> getRoomsByBaseFareLessThan(double maxBaseFare);

    List<RoomDTO> getRoomsByBaseFareBetween(double minBaseFare, double maxBaseFare);

    
   double calculateTotalFare(Room.RoomType roomType, LocalDateTime checkInDate, LocalDateTime checkOutDate, int noOfRooms);

    String getLastAddedRoomNumber(long hotelId);
    
    RoomDTO updateRoom(Long roomId, RoomDTO roomDTO);
    void deleteRoom(Long roomId);

    void updateRoomAvailability(Long roomId, RoomAvailability availability);


}
