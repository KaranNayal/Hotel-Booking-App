package com.hexaware.CozyHeavenStay.service.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.CozyHeavenStay.customexception.HotelOwnerNotFoundException;
import com.hexaware.CozyHeavenStay.customexception.ReservationNotFoundException;
import com.hexaware.CozyHeavenStay.dto.HotelOwnerDTO;
import com.hexaware.CozyHeavenStay.dto.ReservationDTO;
import com.hexaware.CozyHeavenStay.dto.RoomDTO;
import com.hexaware.CozyHeavenStay.entities.HotelOwner;
import com.hexaware.CozyHeavenStay.entities.Reservation;
import com.hexaware.CozyHeavenStay.entities.Room;
import com.hexaware.CozyHeavenStay.repository.HotelOwnerRepository;
import com.hexaware.CozyHeavenStay.repository.ReservationRepository;
import com.hexaware.CozyHeavenStay.repository.RoomRepository;
import com.hexaware.CozyHeavenStay.service.HotelOwnerService;

@Service
public class HotelOwnerServiceImpl implements HotelOwnerService {

    private final HotelOwnerRepository hotelOwnerRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    
    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    public HotelOwnerServiceImpl(HotelOwnerRepository hotelOwnerRepository, RoomRepository roomRepository,
            ReservationRepository reservationRepository, ModelMapper modelMapper) {
        this.hotelOwnerRepository = hotelOwnerRepository;
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public HotelOwnerDTO getHotelOwnerById(Long id) {
        HotelOwner hotelOwner = hotelOwnerRepository.findById(id)
                .orElseThrow(() -> new HotelOwnerNotFoundException("Hotel owner not found with ID: " + id));
        return modelMapper.map(hotelOwner, HotelOwnerDTO.class);
    }

    @Override
    public HotelOwnerDTO updateHotelOwner(Long id, HotelOwnerDTO hotelOwnerDTO) {
        Optional<HotelOwner> existingHotelOwner = hotelOwnerRepository.findById(id);
        if (existingHotelOwner.isPresent()) {
            HotelOwner updatedHotelOwner = modelMapper.map(hotelOwnerDTO, HotelOwner.class);
            updatedHotelOwner.setOwnerId(id);
            return modelMapper.map(hotelOwnerRepository.save(updatedHotelOwner), HotelOwnerDTO.class);
        } else {
            throw new HotelOwnerNotFoundException("Hotel owner not found with ID: " + id);
        }
    }

    @Override
    public void deleteHotelOwner(Long id) {
        hotelOwnerRepository.deleteById(id);
    }

    @Override
    public List<HotelOwnerDTO> getAllHotelOwners() {
        List<HotelOwner> hotelOwners = hotelOwnerRepository.findAll();
        return hotelOwners.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    

    private HotelOwnerDTO convertToDto(HotelOwner hotelOwner) {
        return modelMapper.map(hotelOwner, HotelOwnerDTO.class);
    }
    
    @Override
    public Long findOwnerIdByUsername(String username) {
        Optional<HotelOwner> hotelOwnerOptional = hotelOwnerRepository.findByUserName(username);
        return hotelOwnerOptional.map(HotelOwner::getOwnerId).orElse(null);
    }

    @Override
    public HotelOwnerDTO getHotelOwnerByUsername(String username) {
        Optional<HotelOwner> optionalHotelOwner = hotelOwnerRepository.findByUserName(username);
        if (optionalHotelOwner.isPresent()) {
            HotelOwner hotelOwner = optionalHotelOwner.get();
            // Convert HotelOwner entity to DTO
            HotelOwnerDTO hotelOwnerDTO = new HotelOwnerDTO();
            // Map hotelOwner fields to hotelOwnerDTO fields
            return hotelOwnerDTO;
        } else {
            // Handle case where no hotel owner is found for the given username
            return null;
        }
    }
    
}
