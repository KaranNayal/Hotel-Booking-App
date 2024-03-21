package com.hexaware.CozyHeavenStay.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.CozyHeavenStay.entities.Hotel;
import com.hexaware.CozyHeavenStay.entities.Room.RoomType;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByLocation(String location);
	List<Hotel> findByAmenitiesContaining(String amenity);    
	List<Hotel> findByRoomsRoomType(RoomType roomType);
    static List<Hotel> findByLocationAndHotelNameContaining(String location, String hotelName) {
		return null;
	}
    List<Hotel> findByOwnerId(Long ownerId);
    List<Hotel> findByCity(String city);
}
