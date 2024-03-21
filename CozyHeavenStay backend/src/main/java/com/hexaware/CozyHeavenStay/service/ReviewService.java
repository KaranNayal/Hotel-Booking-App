package com.hexaware.CozyHeavenStay.service;

import com.hexaware.CozyHeavenStay.dto.ReviewDTO;
import java.util.List;

public interface ReviewService {
    ReviewDTO saveReview(ReviewDTO reviewDTO);
    ReviewDTO getReviewById(Long id);
    List<ReviewDTO> getAllReviews();
    List<ReviewDTO> getReviewsByHotelId(Long hotelId);
    List<ReviewDTO> getReviewsByUserId(Long userId);
    List<ReviewDTO> getReviewsByRatingGreaterThan(int rating);
    List<ReviewDTO> getReviewsByRatingLessThan(int rating);
    List<ReviewDTO> getReviewsByHotelAndRating(Long hotelId, int rating);
    List<ReviewDTO> getReviewsByUserAndRating(Long userId, int rating);
    List<ReviewDTO> getReviewsByHotelAndUser(Long hotelId, Long userId);
}
