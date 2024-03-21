package com.hexaware.CozyHeavenStay.service;

import java.util.List;

import com.hexaware.CozyHeavenStay.dto.HotelDTO;
import com.hexaware.CozyHeavenStay.dto.ReviewDTO;
import com.hexaware.CozyHeavenStay.dto.RoomDTO;
import com.hexaware.CozyHeavenStay.entities.Hotel;

public interface HotelService {
  
    HotelDTO getHotelById(Long id);
    List<HotelDTO> getAllHotels();
    HotelDTO updateHotel(Long id, HotelDTO hotelDTO);
    void deleteHotel(Long id);
    List<RoomDTO> getHotelRooms(Long hotelId);
    List<ReviewDTO> getHotelReviews(Long hotelId);
    List<HotelDTO> getHotelsByLocation(String city);
    List<HotelDTO> getHotelsByAmenities(String amenities);
    List<HotelDTO> getHotelsWithRoomType(String roomType);
   // HotelDTO addHotel(HotelDTO hotelDTO);
    
   // HotelDTO addHotel(HotelDTO hotelDTO, Long ownerId);
    HotelDTO addHotel(HotelDTO hotelDTO);
    
    List<HotelDTO> getHotelsByOwnerId(Long ownerId);
    
    


}
