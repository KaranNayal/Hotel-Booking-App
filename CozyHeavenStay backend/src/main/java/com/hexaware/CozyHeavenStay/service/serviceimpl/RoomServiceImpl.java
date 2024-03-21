package com.hexaware.CozyHeavenStay.service.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.CozyHeavenStay.customexception.RoomNotFoundException;
import com.hexaware.CozyHeavenStay.customexception.UserNotFoundException;
import com.hexaware.CozyHeavenStay.dto.ReservationDTO;
import com.hexaware.CozyHeavenStay.dto.ReservationRequestDTO;
import com.hexaware.CozyHeavenStay.dto.RoomDTO;
import com.hexaware.CozyHeavenStay.entities.Hotel;
import com.hexaware.CozyHeavenStay.entities.Reservation;
import com.hexaware.CozyHeavenStay.entities.Room;
import com.hexaware.CozyHeavenStay.entities.Room.RoomAvailability;
import com.hexaware.CozyHeavenStay.entities.Room.RoomType;
import com.hexaware.CozyHeavenStay.entities.User;
import com.hexaware.CozyHeavenStay.repository.HotelRepository;
import com.hexaware.CozyHeavenStay.repository.ReservationRepository;
import com.hexaware.CozyHeavenStay.repository.RoomRepository;
import com.hexaware.CozyHeavenStay.repository.UserRepository;
import com.hexaware.CozyHeavenStay.service.RoomService;

import jakarta.transaction.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final ModelMapper modelMapper;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, UserRepository userRepository,
                           ReservationRepository reservationRepository, ModelMapper modelMapper, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
        this.modelMapper = modelMapper;
        this.hotelRepository= hotelRepository;
    }
    
    @Override
    public RoomDTO saveRoom(RoomDTO roomDTO) {
        // Convert RoomDTO to Room entity
        Room room = new Room();
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setBedType(roomDTO.getBedType());
        room.setAvailability(roomDTO.getAvailability());
        room.setMaxOccupancy(roomDTO.getMaxOccupancy());
        room.setAcStatus(roomDTO.isAcStatus());
        room.setBaseFare(roomDTO.getBaseFare());
        room.setRoomType(roomDTO.getRoomType());

        // Since hotelId is already set in RoomDTO, you can set it directly to the room
        // Get hotel from database using hotelId and set it to the room
        // For example:
         Hotel hotel = hotelRepository.findById(roomDTO.getHotelId()).orElse(null);
         room.setHotel(hotel);

        // Save room entity in the database
        Room savedRoom = roomRepository.save(room);

        // Convert saved Room entity back to RoomDTO and return
        return convertToRoomDTO(savedRoom);
    }

    
    @Override
    public RoomDTO getRoomById(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Room not found with id: " + id));
        return convertToRoomDTO(room);
    }


    @Override
    public List<RoomDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream()
                .map(this::convertToRoomDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDTO> getRoomsByHotelId(Long hotelId) {
        List<Room> rooms = roomRepository.findByHotelHotelId(hotelId);
        return rooms.stream()
                .map(room -> modelMapper.map(room, RoomDTO.class))
                .collect(Collectors.toList());
    }
    
@Override
public List<RoomDTO> getRoomsByType(RoomType type) {
    List<Room> rooms = roomRepository.findByRoomType(type);
    return rooms.stream()
            .map(room -> modelMapper.map(room, RoomDTO.class))
            .collect(Collectors.toList());
}

@Override
public List<RoomDTO> getRoomsByMaxOccupancyGreaterThanEqual(int minOccupancy) {
    List<Room> rooms = roomRepository.findByMaxOccupancyGreaterThanEqual(minOccupancy);
    return rooms.stream()
            .map(room -> modelMapper.map(room, RoomDTO.class))
            .collect(Collectors.toList());
}

@Override
public List<RoomDTO> getRoomsByAcStatus(boolean acStatus) {
    List<Room> rooms = roomRepository.findByAcStatus(acStatus);
    return rooms.stream()
            .map(room -> modelMapper.map(room, RoomDTO.class))
            .collect(Collectors.toList());
}

@Override
public List<RoomDTO> getRoomsByBedType(String bedType) {
    List<Room> rooms = roomRepository.findByBedType(bedType);
    return rooms.stream()
            .map(room -> modelMapper.map(room, RoomDTO.class))
            .collect(Collectors.toList());
}

@Override
public List<RoomDTO> getRoomsByBaseFareLessThan(double maxBaseFare) {
    List<Room> rooms = roomRepository.findByBaseFareLessThan(maxBaseFare);
    return rooms.stream()
            .map(room -> modelMapper.map(room, RoomDTO.class))
            .collect(Collectors.toList());
}

@Override
public List<RoomDTO> getRoomsByBaseFareBetween(double minBaseFare, double maxBaseFare) {
    List<Room> rooms = roomRepository.findByBaseFareBetween(minBaseFare, maxBaseFare);
    return rooms.stream()
            .map(room -> modelMapper.map(room, RoomDTO.class))
            .collect(Collectors.toList());
}

   
 
    public void validateReservationRequest(ReservationRequestDTO reservationRequest) {
        // Implement your validation logic here
        // For example, check if the provided reservation request is valid
        if (reservationRequest == null || reservationRequest.getRoomId() == null ||
                reservationRequest.getNoOfRooms() <= 0 || reservationRequest.getCheckInDate() == null ||
                reservationRequest.getCheckOutDate() == null || reservationRequest.getNoOfAdults() < 0 ||
                reservationRequest.getNoOfChildren() < 0 || reservationRequest.getUserId() == null) {
            throw new IllegalArgumentException("Invalid reservation request data");
        }
    }

    @Override    
    public double calculateTotalFare(Room.RoomType roomType, LocalDateTime checkInDate, LocalDateTime checkOutDate, int noOfRooms) {
        // Calculate duration of stay in days
        long numberOfDays = Duration.between(checkInDate, checkOutDate).toDays();

        // Get the room price based on the room type
        int roomPrice = roomType.getPrice();

        // Calculate total fare based on room rate and duration of stay
        return roomPrice * numberOfDays * noOfRooms;
    }
    
    @Override
    public RoomDTO getRoomByRoomNumberAndHotelId(String roomNumber, Long hotelId) {
        // Retrieve the room entity from the database
        Room room = roomRepository.findByRoomNumberAndHotel_HotelId(roomNumber, hotelId);
        
        // Check if the room is found
        if (room != null) {
            // Convert the Room entity to RoomDTO and return
            return convertToRoomDTO(room);
        } else {
            return null; // Room not found with the given room number and hotel ID
        }
    }

    @Override
    public String getLastAddedRoomNumber(long hotelId) {
        Room room = roomRepository.findFirstByHotel_HotelIdOrderByRoomIdDesc(hotelId);
        if (room != null) {
            return room.getRoomNumber(); // Return the original room number without modifications
        } else {
            return null; // Or any default value indicating no room found
        }
    }


    // Utility method to convert Room entity to RoomDTO
    private RoomDTO convertToRoomDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomId(room.getRoomId());
        roomDTO.setRoomNumber(room.getRoomNumber());
        roomDTO.setBedType(room.getBedType());
        roomDTO.setAvailability(room.getAvailability());
        roomDTO.setMaxOccupancy(room.getMaxOccupancy());
        roomDTO.setAcStatus(room.isAcStatus());
        roomDTO.setBaseFare(room.getBaseFare());
        roomDTO.setRoomType(room.getRoomType());
        roomDTO.setHotelId(room.getHotel().getHotelId());
        return roomDTO;
    }
    
    
    @Override
    public RoomDTO updateRoom(Long roomId, RoomDTO roomDTO) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (!optionalRoom.isPresent()) {
            throw new RoomNotFoundException("Room not found with id: " + roomId);
        }
        Room room = optionalRoom.get();
        // Update room properties
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setBedType(roomDTO.getBedType());
        // Update other properties as needed

        // Save updated room
        Room updatedRoom = roomRepository.save(room);
        return convertToRoomDTO(updatedRoom);
    }

    @Override
    public void deleteRoom(Long roomId) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (!optionalRoom.isPresent()) {
            throw new RoomNotFoundException("Room not found with id: " + roomId);
        }
        roomRepository.deleteById(roomId);
    }
    
    
    @Transactional
    @Override
    public void updateRoomAvailability(Long roomId, RoomAvailability availability) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            room.setAvailability(availability);
            roomRepository.save(room);
        } else {
            throw new IllegalArgumentException("Room with ID " + roomId + " not found.");
        }
    }


    
}
