package com.hexaware.CozyHeavenStay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hexaware.CozyHeavenStay.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	  List<Review> findByHotelId(Long hotelId);

	  List<Review> findByUserId(Long userId);
  	   
	  List<Review> findByRatingLessThan(int maxRating);

	  List<Review> findByRatingGreaterThan(int rating);

	  List<Review> findByHotelIdAndRating(Long hotelId, int rating);

	  List<Review> findByUserIdAndRating(Long userId, int rating);

	  List<Review> findByHotelIdAndUserId(Long hotelId, Long userId);



	    }

