package com.hexaware.CozyHeavenStay.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.CozyHeavenStay.entities.HotelOwner;

@Repository
public interface HotelOwnerRepository extends JpaRepository<HotelOwner, Long> {

	 Optional<HotelOwner> findByUserName(String userName);

	    List<HotelOwner> findByHotelId(Long hotelId);

		boolean existsByUserName(String userName);

		boolean existsByEmail(String email);
	    
	
}
