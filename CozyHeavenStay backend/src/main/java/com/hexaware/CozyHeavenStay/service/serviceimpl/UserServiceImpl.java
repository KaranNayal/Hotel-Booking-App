package com.hexaware.CozyHeavenStay.service.serviceimpl;

import com.hexaware.CozyHeavenStay.customexception.AlreadyBookedException;
import com.hexaware.CozyHeavenStay.customexception.ReservationNotFoundException;
import com.hexaware.CozyHeavenStay.customexception.RoomNotFoundException;
import com.hexaware.CozyHeavenStay.customexception.UserNotFoundException;
import com.hexaware.CozyHeavenStay.dto.HotelDTO;
import com.hexaware.CozyHeavenStay.dto.ReservationDTO;
import com.hexaware.CozyHeavenStay.dto.ReservationRequestDTO;
import com.hexaware.CozyHeavenStay.dto.SearchRequestDTO;
import com.hexaware.CozyHeavenStay.dto.UserDTO;
import com.hexaware.CozyHeavenStay.entities.Hotel;
import com.hexaware.CozyHeavenStay.entities.Reservation;
import com.hexaware.CozyHeavenStay.entities.Room;
import com.hexaware.CozyHeavenStay.entities.Room.RoomType;
import com.hexaware.CozyHeavenStay.entities.User;
import com.hexaware.CozyHeavenStay.repository.HotelRepository;
import com.hexaware.CozyHeavenStay.repository.ReservationRepository;
import com.hexaware.CozyHeavenStay.repository.RoomRepository;
import com.hexaware.CozyHeavenStay.repository.UserRepository;
import com.hexaware.CozyHeavenStay.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    @Autowired
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,RoomRepository roomRepository, ReservationRepository reservationRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(modelMapper.map(user, UserDTO.class));
        }
        return userDTOs;
    }

   

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        // Hash the password before saving
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        User user = modelMapper.map(userDTO, User.class);
        user = userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        if (userRepository.existsById(userId)) {
            // Hash the password if it's being updated
            if (userDTO.getPassword() != null) {
                userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }

            User updatedUser = modelMapper.map(userDTO, User.class);
            updatedUser.setUserId(userId);
            return modelMapper.map(userRepository.save(updatedUser), UserDTO.class);
        }
        return null;
    }
   
    
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        
        // Remove the user from all roles
        user.getRoles().clear(); // Assuming roles are fetched eagerly

        // Delete the user
        userRepository.delete(user);
    }
    
    

   

    @Override
    public List<UserDTO> getUsersByGender(String gender) {
        List<User> users = userRepository.findByGender(gender);
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(modelMapper.map(user, UserDTO.class));
        }
        return userDTOs;
    }

    @Override
    public List<UserDTO> getUsersByAddress(String address) {
        List<User> users = userRepository.findByAddress(address);
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(modelMapper.map(user, UserDTO.class));
        }
        return userDTOs;
    }

    @Override
    public List<UserDTO> getUsersByContactNo(String contactNo) {
        List<User> users = userRepository.findByContactNo(contactNo);
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(modelMapper.map(user, UserDTO.class));
        }
        return userDTOs;
    }

    @Override
    public List<UserDTO> getUsersByUserNameContaining(String keyword) {
        List<User> users = userRepository.findByUserNameContaining(keyword);
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(modelMapper.map(user, UserDTO.class));
        }
        return userDTOs;
    }


    @Override
    public List<HotelDTO> searchHotels(SearchRequestDTO searchRequest) {
        List<HotelDTO> hotelDTOs = new ArrayList<>();
        // Implement search logic based on search criteria
        List<Hotel> hotels = HotelRepository.findByLocationAndHotelNameContaining(searchRequest.getLocation(), searchRequest.getHotelName());
        for (Hotel hotel : hotels) {
            hotelDTOs.add(modelMapper.map(hotel, HotelDTO.class));
        }
        return hotelDTOs;
    }


//    @Override
//    public ReservationDTO makeReservation(ReservationRequestDTO reservationRequestDTO) {
//        // Validate the request data
//        if (reservationRequestDTO == null || reservationRequestDTO.getRoomId() == null ||
//                reservationRequestDTO.getNoOfRooms() <= 0 || reservationRequestDTO.getCheckInDate() == null ||
//                reservationRequestDTO.getCheckOutDate() == null || reservationRequestDTO.getNoOfAdults() < 0 ||
//                reservationRequestDTO.getNoOfChildren() < 0 || reservationRequestDTO.getUserId() == null) {
//            throw new IllegalArgumentException("Invalid reservation request data");
//        }
//
//         // Retrieve user and room details from the database
//        User user = userRepository.findById(reservationRequestDTO.getUserId())
//                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + reservationRequestDTO.getUserId()));
//        Room room = roomRepository.findById(reservationRequestDTO.getRoomId())
//                .orElseThrow(() -> new RoomNotFoundException("Room not found with ID: " + reservationRequestDTO.getRoomId()));
//
//        // Check if the requested rooms are already booked for the given dates
//        List<Reservation> existingReservations = reservationRepository.findByRoomIdAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(
//                reservationRequestDTO.getRoomId(),
//                reservationRequestDTO.getCheckOutDate(),
//                reservationRequestDTO.getCheckInDate());
//
//        if (!existingReservations.isEmpty()) {
//            throw new AlreadyBookedException("The requested room(s) are already booked for the given dates");
//        }
//
//        // Calculate total fare based on room type enum
//        double totalFare = calculateTotalFare(room.getRoomType(), 
//                reservationRequestDTO.getCheckInDate(),
//                reservationRequestDTO.getCheckOutDate(), 
//                reservationRequestDTO.getNoOfRooms(), 
//                reservationRequestDTO.getNoOfAdults(), 
//                reservationRequestDTO.getNoOfChildren());
//   
//        // Create a new Reservation entity
//        Reservation reservation = new Reservation();
//        reservation.setRoomId(reservationRequestDTO.getRoomId());
//        reservation.setNoOfRooms(reservationRequestDTO.getNoOfRooms());
//        reservation.setNoOfAdults(reservationRequestDTO.getNoOfAdults());
//        reservation.setNoOfChildrens(reservationRequestDTO.getNoOfChildren());
//        reservation.setCheckInDate(reservationRequestDTO.getCheckInDate());
//        reservation.setCheckOutDate(reservationRequestDTO.getCheckOutDate());
//        reservation.setTotalFare(totalFare);
//        reservation.setUser(user); // Set the User object for the reservation
//
//         // Set the status of the reservation to "Pending"
//        reservation.setStatus("Confirmed");
//
//        // Check if the payment status is "Confirmed" and set booking status to "Confirmed" accordingly
////        if (reservationRequestDTO.getPaymentStatus().equals("Confirmed")) {
////            reservation.setStatus("Confirmed");
////        }
//
//        // Save the reservation to the database
//        Reservation savedReservation = reservationRepository.save(reservation);
//        return modelMapper.map(savedReservation, ReservationDTO.class);
//    }

    public double calculateTotalFare(Room.RoomType roomType, LocalDateTime checkInDate, LocalDateTime checkOutDate, int noOfRooms, int noOfAdults, int noOfChildren) {
        // Calculate duration of stay in days
        long numberOfDays = Duration.between(checkInDate, checkOutDate).toDays();

        // Get the base room price based on the room type
        int baseRoomPrice = roomType.getPrice();

        // Calculate base fare for the room
        double baseFare = baseRoomPrice * numberOfDays * noOfRooms;

        // Determine the maximum occupancy based on the room type
        int maxOccupancy;
        if (roomType == Room.RoomType.STANDARD) {
            maxOccupancy = 2;
        } else if (roomType == Room.RoomType.DELUXE) {
            maxOccupancy = 4;
        } else {
            maxOccupancy = 6;
        }

        // Calculate additional fare for additional occupants
        int totalOccupants = noOfAdults + noOfChildren;
        double additionalFare = 0.0;
        if (totalOccupants > maxOccupancy) {
            int additionalOccupants = totalOccupants - maxOccupancy;
            // Calculate additional fare based on occupant age
            for (int i = 0; i < additionalOccupants; i++) {
                double occupantAdditionalFare;
                if (noOfAdults >= 14) {
                    occupantAdditionalFare = 0.4 * baseRoomPrice;
                } else {
                    occupantAdditionalFare = 0.2 * baseRoomPrice;
                }
                additionalFare += occupantAdditionalFare;
            }
        }

        // Calculate total fare including number of days
        double totalFare = baseFare + additionalFare;

        return totalFare;
    }




    @Override
    public void cancelReservation(Long reservationId) {
        // Check if the reservation exists
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            // Implement cancellation logic here (e.g., update reservation status)
            reservation.setStatus("Cancelled");
            // Save the updated reservation
            reservationRepository.save(reservation);
        } else {
            throw new ReservationNotFoundException("Reservation not found with ID: " + reservationId);
        }
    }

//    @Override
//    public List<ReservationDTO> getReservationHistory(Long userId) {
//        // Fetch the user from the database
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
//
//        // Fetch the reservation history for the user
//        List<Reservation> reservations = reservationRepository.findByUserUserId(userId);
//        return reservations.stream()
//                .map(reservation -> modelMapper.map(reservation, ReservationDTO.class))
//                .collect(Collectors.toList());
//    }
    
    @Override
    public User findUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUserName(username);
        return userOptional.orElse(null); // or handle the case when the user is not found
    }

    @Override
    public Long findUserIdByUsername(String username) {
        User user = userRepository.findByUserName(username).orElse(null);
        if (user != null) {
            return user.getUserId();
        }
        return null; // Return null if user not found
    }
    @Override
    public UserDTO getUserByUsername(String userName) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + userName));
        return modelMapper.map(user, UserDTO.class);
    }

}