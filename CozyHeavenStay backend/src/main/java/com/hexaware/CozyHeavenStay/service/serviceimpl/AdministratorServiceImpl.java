package com.hexaware.CozyHeavenStay.service.serviceimpl;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.CozyHeavenStay.customexception.ReservationNotFoundException;
import com.hexaware.CozyHeavenStay.dto.AdministratorDTO;
import com.hexaware.CozyHeavenStay.dto.ReservationDTO;
import com.hexaware.CozyHeavenStay.entities.Administrator;
import com.hexaware.CozyHeavenStay.entities.Reservation;
import com.hexaware.CozyHeavenStay.repository.AdministratorRepository;
import com.hexaware.CozyHeavenStay.repository.HotelOwnerRepository;
import com.hexaware.CozyHeavenStay.repository.ReservationRepository;
import com.hexaware.CozyHeavenStay.repository.UserRepository;
import com.hexaware.CozyHeavenStay.service.AdministratorService;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    private final AdministratorRepository administratorRepository;

    @Autowired
    public AdministratorServiceImpl(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelOwnerRepository hotelOwnerRepository;

    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public AdministratorDTO getAdministratorById(Long id) {
        Administrator administrator = administratorRepository.findById(id).orElse(null);
        return mapToDTO(administrator);
    }

    @Override
    public AdministratorDTO updateAdministrator(Long id, AdministratorDTO administratorDTO) {
        if (administratorRepository.existsById(id)) {
            Administrator administrator = mapToEntity(administratorDTO);
            administrator.setAdminId(id);
            Administrator updatedAdministrator = administratorRepository.save(administrator);
            return mapToDTO(updatedAdministrator);
        }
        return null; // or throw an exception indicating administrator not found
    }

    @Override
    public void deleteAdministrator(Long id) {
        administratorRepository.deleteById(id);
    }


    @Override
    public List<AdministratorDTO> getAllAdmins() {
        List<Administrator> admins = administratorRepository.findAll();
        return admins.stream()
                .map(admin -> modelMapper.map(admin, AdministratorDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDTO> getAllBookings() {
        List<Reservation> reservations = reservationRepository.findAll();
        return mapReservationsToDTOs(reservations);
    }
    
    @Override
    public void deleteBooking(Long bookingId) {
        // Find the booking by its ID
        Optional<Reservation> bookingOptional = reservationRepository.findById(bookingId);

        // Check if the booking exists
        if (bookingOptional.isPresent()) {
            Reservation booking = bookingOptional.get();

            // Delete the booking
            reservationRepository.delete(booking);
        } else {
            // Handle the case where the booking does not exist
            throw new ReservationNotFoundException("Booking with ID " + bookingId + " not found");
        }
    }
    

    
    private AdministratorDTO mapToDTO(Administrator administrator) {
        if (administrator == null) {
            return null;
        }
        return modelMapper.map(administrator, AdministratorDTO.class);
    }

    private Administrator mapToEntity(AdministratorDTO administratorDTO) {
        if (administratorDTO == null) {
            return null;
        }
        return modelMapper.map(administratorDTO, Administrator.class);
    }
    
    private List<ReservationDTO> mapReservationsToDTOs(List<Reservation> reservations) {
        return reservations.stream()
                .map(this::mapReservationToDTO)
                .collect(Collectors.toList());
    }

    private ReservationDTO mapReservationToDTO(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDTO.class);
    }
    
    @Override
    public Long findAdminIdByUsername(String username) {
        Optional<Administrator> administratorOptional = administratorRepository.findByUserName(username);
        return administratorOptional.map(Administrator::getAdminId).orElse(null);
    }
    
}



