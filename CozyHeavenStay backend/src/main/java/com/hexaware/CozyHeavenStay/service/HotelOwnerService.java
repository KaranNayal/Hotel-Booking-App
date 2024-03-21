package com.hexaware.CozyHeavenStay.service;

import com.hexaware.CozyHeavenStay.dto.HotelOwnerDTO;
import com.hexaware.CozyHeavenStay.dto.ReservationDTO;
import com.hexaware.CozyHeavenStay.dto.RoomDTO;

import java.util.List;

public interface HotelOwnerService {
   
    HotelOwnerDTO getHotelOwnerById(Long id);
    HotelOwnerDTO updateHotelOwner(Long id, HotelOwnerDTO hotelOwnerDTO);
    void deleteHotelOwner(Long id);
    List<HotelOwnerDTO> getAllHotelOwners();
  
    Long findOwnerIdByUsername(String userName);
    
    HotelOwnerDTO getHotelOwnerByUsername(String userName);
  
}
