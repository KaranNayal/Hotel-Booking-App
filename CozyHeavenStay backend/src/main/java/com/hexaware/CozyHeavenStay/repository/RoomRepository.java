 package com.hexaware.CozyHeavenStay.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.CozyHeavenStay.dto.RoomDTO;
import com.hexaware.CozyHeavenStay.entities.Room;
import com.hexaware.CozyHeavenStay.entities.Room.RoomAvailability;
import com.hexaware.CozyHeavenStay.entities.Room.RoomType;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	
	 List<Room> findByRoomType(RoomType roomType);

	 List<Room> findByMaxOccupancyGreaterThanEqual(int minOccupancy);

	 List<Room> findByAcStatus(boolean acStatus);
	 
	 List<Room> findByBedType(String bedType);

	 List<Room> findByBaseFareLessThan(double maxBaseFare);
	 
	 List<Room> findByBaseFareBetween(double minBaseFare, double maxBaseFare);

	List<Room> findByHotelHotelId(Long hotelId);

	Room findFirstByHotel_HotelIdOrderByRoomIdDesc(Long hotelId);
   
    Room findByRoomNumberAndHotel_HotelId(String roomNumber, Long hotelId);

	@Query("SELECT r FROM Room r WHERE r.hotel.hotelId = :hotelId AND r.roomType = :roomType AND r.bedType = :bedType AND r.acStatus = :acStatus AND r.availability = :availability")
	List<Room> findAvailableRoomsByHotelAndTypeAndBedAndAc(@Param("hotelId") Long hotelId, @Param("roomType") RoomType roomType, @Param("bedType") String bedType, @Param("acStatus") boolean acStatus, @Param("availability") RoomAvailability availability);
	
    

}