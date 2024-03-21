package com.hexaware.CozyHeavenStay.service;

import java.util.List;
import com.hexaware.CozyHeavenStay.dto.AdministratorDTO;
import com.hexaware.CozyHeavenStay.dto.ReservationDTO;

public interface AdministratorService {
    
    AdministratorDTO getAdministratorById(Long id);
    AdministratorDTO updateAdministrator(Long id, AdministratorDTO administratorDTO);
    void deleteAdministrator(Long id);
    List<AdministratorDTO> getAllAdmins();
    
    List<ReservationDTO> getAllBookings();
    void deleteBooking(Long bookingId);
  
    Long findAdminIdByUsername(String username);
}
