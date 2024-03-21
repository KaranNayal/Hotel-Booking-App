package com.hexaware.CozyHeavenStay.service.serviceimpl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hexaware.CozyHeavenStay.dto.ReservationDTO;
import com.hexaware.CozyHeavenStay.dto.ReservationRequestDTO;
import com.hexaware.CozyHeavenStay.dto.RoomDTO;
import com.hexaware.CozyHeavenStay.dto.UserDTO;
import com.hexaware.CozyHeavenStay.entities.Reservation;
import com.hexaware.CozyHeavenStay.entities.Room;
import com.hexaware.CozyHeavenStay.entities.User;
import com.hexaware.CozyHeavenStay.entities.Room.RoomAvailability;
import com.hexaware.CozyHeavenStay.entities.Room.RoomType;
import com.hexaware.CozyHeavenStay.repository.ReservationRepository;
import com.hexaware.CozyHeavenStay.repository.RoomRepository;
import com.hexaware.CozyHeavenStay.repository.UserRepository;
import com.hexaware.CozyHeavenStay.service.ReservationService;
import com.hexaware.CozyHeavenStay.service.RoomService;
import com.hexaware.CozyHeavenStay.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomService roomService;
    private final UserService userService;
    private final RoomRepository roomRepository;
  
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, ModelMapper modelMapper,RoomService roomService, UserService userService, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.roomService = roomService;
        this.userService = userService;
        this.roomRepository=roomRepository;
        this.modelMapper = modelMapper;

    }
    
    
    @Override
    public List<Reservation> makeReservation(Long hotelId, Long userId, RoomType roomType, String bedType, boolean acStatus, LocalDateTime checkInDate, LocalDateTime checkOutDate, int noOfRooms, int noOfAdults, int noOfChildren, double totalFare) {
        // Find available rooms based on provided parameters
        List<Room> availableRooms = roomRepository.findAvailableRoomsByHotelAndTypeAndBedAndAc(hotelId, roomType, bedType, acStatus, RoomAvailability.AVAILABLE);

        // Calculate the duration of stay
        long duration = Duration.between(checkInDate, checkOutDate).toDays();

        // Create a list to store reservations
        List<Reservation> reservations = new ArrayList<>();

        // Iterate through available rooms to find suitable rooms with enough occupancy
        int roomsToAllocate = Math.min(availableRooms.size(), noOfRooms);
        for (int i = 0; i < roomsToAllocate; i++) {
            Room selectedRoom = availableRooms.get(i);
            
            // Check if the selected room can accommodate the requested number of guests
            if (selectedRoom.getMaxOccupancy() >= noOfAdults + noOfChildren) {
                // Create a new reservation
                Reservation reservation = new Reservation();
                reservation.setRoomId(selectedRoom.getRoomId());
                reservation.setNoOfRooms(1); // Each reservation is for one room
                reservation.setNoOfAdults(noOfAdults);
                reservation.setNoOfChildrens(noOfChildren);
                reservation.setStatus("CONFIRMED");
                reservation.setCheckInDate(checkInDate);
                reservation.setCheckOutDate(checkOutDate);
				reservation.setTotalFare(totalFare); 


                // Fetch the user entity based on userId
                User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
                
                // Set the user in the reservation
                reservation.setUser(user);

                // Save reservation to database
                reservationRepository.save(reservation);

                // Update room availability to BOOKED
                selectedRoom.setAvailability(RoomAvailability.BOOKED);
                roomRepository.save(selectedRoom);

                // Add reservation to the list
                reservations.add(reservation);
            }
            else {
                throw new IllegalArgumentException("Selected room cannot accommodate the requested number of guests.");
            }
        }

        return reservations;
    }

//    @Scheduled(fixedRate = 60000) // Runs every minute
//    @Transactional // Ensure that the method executes within a transactional context
//    public void updateRoomAvailability() {
//        LocalDateTime currentDateTime = LocalDateTime.now();
//
//        // Find reservations with checkout date in the past
//        List<Reservation> expiredReservations = reservationRepository.findByCheckOutDateBeforeAndStatus(currentDateTime, "CONFIRMED");
//
//        for (Reservation reservation : expiredReservations) {
//            // Ensure that the rooms collection is loaded
//            reservation.getRooms().size(); // Force loading of the rooms collection
//
//            if (!reservation.getRooms().isEmpty()) {
//                // Update room availability to AVAILABLE
//                Room room = reservation.getRooms().get(0); // Assuming one reservation is linked to one room
//                room.setAvailability(RoomAvailability.AVAILABLE);
//                roomRepository.save(room);
//
//                // Update reservation status to reflect completion
//                reservation.setStatus("CHECKED OUT");
//                reservationRepository.save(reservation);
//            }
//        }
//    }


  	@Override
    public ReservationDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with ID: " + id));
        return modelMapper.map(reservation, ReservationDTO.class);
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(reservation -> modelMapper.map(reservation, ReservationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDTO> getUserReservations(Long userId) {
        List<Reservation> reservations = reservationRepository.findByUserUserId(userId);
        return reservations.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    private ReservationDTO mapToDTO(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        // Map reservation entity fields to DTO fields
        dto.setReservationId(reservation.getReservationId());
        dto.setRoomId(reservation.getRoomId());
        dto.setNoOfRooms(reservation.getNoOfRooms());
        dto.setNoOfChildrens(reservation.getNoOfChildrens());
        dto.setNoOfAdults(reservation.getNoOfAdults());
        dto.setStatus(reservation.getStatus());
        dto.setCheckInDate(reservation.getCheckInDate());
        dto.setCheckOutDate(reservation.getCheckOutDate());
        dto.setTotalFare(reservation.getTotalFare());
        // Map other fields accordingly
        return dto;
    }

    
    @Override
    public ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with ID: " + id));
        Reservation updatedReservation = modelMapper.map(reservationDTO, Reservation.class);
        updatedReservation.setReservationId(id);
        return modelMapper.map(reservationRepository.save(updatedReservation), ReservationDTO.class);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }



	}	


