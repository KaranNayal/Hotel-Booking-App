package com.hexaware.CozyHeavenStay.repository;
import com.hexaware.CozyHeavenStay.dto.AdministratorDTO;
import com.hexaware.CozyHeavenStay.dto.RoomDTO;
import com.hexaware.CozyHeavenStay.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class JPAQueriesTest {
	 @Autowired
	    AdministratorRepository administratorRepository;

	    @Autowired
	    UserRepository userRepository;

	    @Autowired
	    HotelRepository hotelRepository;

	    @Autowired
	    RoomRepository roomRepository;

	    @Autowired
	    ReservationRepository reservationRepository;

	    @Autowired
	    ReviewRepository reviewRepository;
	    
	    @Autowired
	    HotelOwnerRepository hotelOwnerRepository;

//	    @Test
//	    void saveMethodTest() {
//	        Administrator admin = new Administrator();
//	        admin.setUserName("admin1");
//	        admin.setPassword("password1");
//	        admin.sethotelCity("New York");
//	        admin.sethotelRating(4);
//
//	        Administrator savedAdmin = administratorRepository.save(admin);
//	        Assertions.assertNotNull(savedAdmin);
//	    }


	    @Test
	    void saveUserTest() {
	        User user = new User();
	        user.setUserName("user1");
	        user.setPassword("password1");
	        user.setEmail("user1@example.com");
	        user.setGender("Male");
	        user.setAddress("123 Street, City");
	        user.setContactNo("8976512345");

	        User savedUser = userRepository.save(user);
	        Assertions.assertNotNull(savedUser);
	    }

//	    @Test
//	    void saveHotelTest() {
//	        Hotel hotel = new Hotel();
//	        hotel.setHotelName("Hotel ABC");
//	        hotel.setLocation("New York");
//	        hotel.setAmenities("Pool, Gym, Spa");
//
//	        Hotel savedHotel = hotelRepository.save(hotel);
//	        Assertions.assertNotNull(savedHotel);
//	    }

//	    @Test
//	    void saveRoomTest() {
//	        Room room = new Room();
//	        room.setType(RoomType.STANDARD);
//	        room.setMaxOccupancy(2);
//	        room.setAcStatus(true);
//	        room.setBaseFare(4000.00);
//	        room.setHotel(hotelRepository.findById(1L).orElse(null)); // Assuming hotel with id 1 exists
//
//	        Room savedRoom = roomRepository.save(room);
//	        Assertions.assertNotNull(savedRoom);
//	    }
	    
	    @Test
	    void findByLocationTest() {
	        List<Hotel> hotels = hotelRepository.findByLocation("New York");
	        Assertions.assertNotNull(hotels);
	    }

	    @Test
	    void findByAmenitiesContainingTest() {
	        List<Hotel> hotels = hotelRepository.findByAmenitiesContaining("Pool");
	        Assertions.assertNotNull(hotels);
	    }

//	    @Test
//	    void findByRoomsRoomTypeTest() {
//	        List<Hotel> hotels = hotelRepository.findByRoomsRoomType(RoomType.STANDARD);
//	        Assertions.assertNotNull(hotels);
//	    }

//	    @Test
//	    void findOverlappingReservationsTest() {
//	        LocalDateTime checkInDate = LocalDateTime.now();
//	        LocalDateTime checkOutDate = checkInDate.plusDays(2);
//	        List<Reservation> reservations = reservationRepository.findOverlappingReservations(1L, checkInDate, checkOutDate);
//	        Assertions.assertNotNull(reservations);
//	    }

	    @Test
	    void findByRatingGreaterThanTest() {
	        List<Review> reviews = reviewRepository.findByRatingGreaterThan(4);
	        Assertions.assertNotNull(reviews);
	    }
//	    @Test
//	    void findByHotelCityTest() {
//	        List<Administrator> admins = administratorRepository.findByHotelCity("New York");
//	        Assertions.assertNotNull(admins);
//	    }

//	    @Test
//	    void findByHotelRatingGreaterThanTest() {
//	        List<AdministratorDTO> admins = administratorRepository.findByHotelRatingGreaterThan(3);
//	        Assertions.assertNotNull(admins);
//	    }

	    @Test
	    void findByHotelIdTest() {
	        Long hotelId = 1L; // Assuming hotel with id 1 exists
	        List<HotelOwner> hotelOwners = hotelOwnerRepository.findByHotelId(hotelId);
	        Assertions.assertNotNull(hotelOwners);
	    }

	    @Test
	    void findByTypeTest() {
	        List<Room> rooms = roomRepository.findByRoomType(Room.RoomType.DELUXE);
	        Assertions.assertNotNull(rooms);
	    }

	    @Test
	    void findByMaxOccupancyGreaterThanEqualTest() {
	        List<Room> rooms = roomRepository.findByMaxOccupancyGreaterThanEqual(2);
	        Assertions.assertNotNull(rooms);
	    }

	    @Test
	    void findByAcStatusTest() {
	        List<Room> rooms = roomRepository.findByAcStatus(true);
	        Assertions.assertNotNull(rooms);
	    }

	    @Test
	    void findByBedTypeTest() {
	        List<Room> rooms = roomRepository.findByBedType("Queen");
	        Assertions.assertNotNull(rooms);
	    }

	    @Test
	    void findByBaseFareLessThanTest() {
	        List<Room> rooms = roomRepository.findByBaseFareLessThan(200);
	        Assertions.assertNotNull(rooms);
	    }

	    @Test
	    void findByBaseFareBetweenTest() {
	        List<Room> rooms = roomRepository.findByBaseFareBetween(100, 200);
	        Assertions.assertNotNull(rooms);
	    }

	    @Test
	    void findByHotelHotelIdTest() {
	        Long hotelId = 1L; // Assuming hotel with id 1 exists
	        List<Room> rooms = roomRepository.findByHotelHotelId(hotelId);
	        Assertions.assertNotNull(rooms);
	    }

	    @Test
	    void findByUserIdTest() {
	        Long userId = 1L; // Assuming user with id 1 exists
	        List<Review> reviews = reviewRepository.findByUserId(userId);
	        Assertions.assertNotNull(reviews);
	    }

	    @Test
	    void findByHotelIdAndUserIdTest() {
	        Long hotelId = 1L; // Assuming hotel with id 1 exists
	        Long userId = 1L; // Assuming user with id 1 exists
	        List<Review> reviews = reviewRepository.findByHotelIdAndUserId(hotelId, userId);
	        Assertions.assertNotNull(reviews);
	    }
	   
	    @Test
	    void findByHotelIdAndRatingTest() {
	        Long hotelId = 1L; // Assuming hotel with id 1 exists
	        int rating = 4;
	        List<Review> reviewsForHotelWithRating = reviewRepository.findByHotelIdAndRating(hotelId, rating);
	        assertNotNull(reviewsForHotelWithRating);
	    }
	    @Test
	    void saveReservationTest() {
	        // Create a new reservation
	        Reservation reservation = new Reservation();
	        reservation.setRoomId(1L); // Assuming room with ID 1 exists
	        reservation.setNoOfRooms(1);
	        reservation.setNoOfChildrens(0);
	        reservation.setNoOfAdults(1);
	        reservation.setStatus("CONFIRMED");
	        reservation.setCheckInDate(LocalDateTime.of(2024, 2, 17, 10, 0)); // Check-in date
	        reservation.setCheckOutDate(LocalDateTime.of(2024, 2, 20, 10, 0)); // Check-out date
	        reservation.setTotalFare(4000.00);

	        // Save the reservation
	        Reservation savedReservation = reservationRepository.save(reservation);

	        // Verify that the saved reservation is not null
	        Assertions.assertNotNull(savedReservation);

	        // Optionally, you can further validate by fetching the saved reservation from the database
	        Optional<Reservation> fetchedReservation = reservationRepository.findById(savedReservation.getReservationId());

	        // Check if the fetched reservation is present and matches the saved reservation
	        Assertions.assertTrue(fetchedReservation.isPresent());

	        // Get the fetched reservation
	        Reservation fetched = fetchedReservation.get();

	        // Assert that the fetched reservation's attributes match the original reservation's attributes
	        Assertions.assertEquals(reservation.getReservationId(), fetched.getReservationId());
	        Assertions.assertEquals(reservation.getRoomId(), fetched.getRoomId());
	        Assertions.assertEquals(reservation.getNoOfRooms(), fetched.getNoOfRooms());
	        Assertions.assertEquals(reservation.getNoOfChildrens(), fetched.getNoOfChildrens());
	        Assertions.assertEquals(reservation.getNoOfAdults(), fetched.getNoOfAdults());
	        Assertions.assertEquals(reservation.getStatus(), fetched.getStatus());
	        Assertions.assertEquals(reservation.getTotalFare(), fetched.getTotalFare());
	        Assertions.assertEquals(reservation.getCheckInDate(), fetched.getCheckInDate());
	        Assertions.assertEquals(reservation.getCheckOutDate(), fetched.getCheckOutDate());
	    }
//	    @Test
//	    void findByStatusTest() {
//	        // Assuming there are reservations with the status "CONFIRMED" in the database
//	        String status = "CONFIRMED";
//	        List<Reservation> confirmedReservations = reservationRepository.findByStatus(status);
//	        Assertions.assertFalse(confirmedReservations.isEmpty());
//	    }
	    @Test
	    void saveHotelOwnerTest() {
	        // Save
	        HotelOwner hotelOwner = new HotelOwner();
	        hotelOwner.setUserName("test_owner");
	        hotelOwner.setPassword("password123");
	        HotelOwner savedHotelOwner = hotelOwnerRepository.save(hotelOwner);
	        Assertions.assertNotNull(savedHotelOwner.getOwnerId());
	    }

	  


	    @Test
	    void updateHotelOwnerTest() {
	        // Update
	        Long ownerId = 1L; // Assuming owner with id 1 exists
	        Optional<HotelOwner> foundHotelOwner = hotelOwnerRepository.findById(ownerId);
	        if (foundHotelOwner.isPresent()) {
	            HotelOwner hotelOwner = foundHotelOwner.get();
	            hotelOwner.setUserName("updated_owner");
	            HotelOwner updatedHotelOwner = hotelOwnerRepository.save(hotelOwner);
	            Assertions.assertEquals("updated_owner", updatedHotelOwner.getUserName());
	        }
	    }

	    @Test
	    void deleteHotelOwnerTest() {
	        // Delete
	        Long ownerId = 1L; // Assuming owner with id 1 exists
	        hotelOwnerRepository.deleteById(ownerId);
	        Optional<HotelOwner> deletedHotelOwner = hotelOwnerRepository.findById(ownerId);
	        Assertions.assertFalse(deletedHotelOwner.isPresent());
	    }

	    @Test
	    void findAllHotelOwnersTest() {
	        // Find All
	        List<HotelOwner> hotelOwners = hotelOwnerRepository.findAll();
	        Assertions.assertFalse(hotelOwners.isEmpty());
	    }
	   
	    @Test
	    void findByGenderTest() {
	        String gender = "Male"; // Replace with an existing gender in your database
	        List<User> users = userRepository.findByGender(gender);
	        Assertions.assertFalse(users.isEmpty());
	    }

	    @Test
	    void updateUserTest() {
	        // Update
	        Long userId = 1L; // Assuming user with id 1 exists
	        Optional<User> foundUser = userRepository.findById(userId);
	        if (foundUser.isPresent()) {
	            User user = foundUser.get();
	            user.setUserName("updated_user");
	            User updatedUser = userRepository.save(user);
	            Assertions.assertEquals("updated_user", updatedUser.getUserName());
	        }
	    }

	    @Test
	    void deleteUserTest() {
	        // Delete
	        Long userId = 1L; // Assuming user with id 1 exists
	        userRepository.deleteById(userId);
	        Optional<User> deletedUser = userRepository.findById(userId);
	        Assertions.assertFalse(deletedUser.isPresent());
	    }

	    @Test
	    void findAllUsersTest() {
	        // Find All
	        List<User> users = userRepository.findAll();
	        Assertions.assertFalse(users.isEmpty());
	    }
	    

}