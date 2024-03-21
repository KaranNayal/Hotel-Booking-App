package com.hexaware.CozyHeavenStay.controller;

import com.hexaware.CozyHeavenStay.dto.ReviewDTO;
import com.hexaware.CozyHeavenStay.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/save")
    public ReviewDTO saveReview(@RequestBody ReviewDTO reviewDTO) {
        return reviewService.saveReview(reviewDTO);
    }

    @GetMapping("/getreviewbyid/{id}")
    public ReviewDTO getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    @GetMapping("/getallreviews")
    public List<ReviewDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/getreviewsbyhotel/{hotelId}")
    public List<ReviewDTO> getReviewsByHotelId(@PathVariable Long hotelId) {
        return reviewService.getReviewsByHotelId(hotelId);
    }

    @GetMapping("/getreviewsbyuser/{userId}")
    public List<ReviewDTO> getReviewsByUserId(@PathVariable Long userId) {
        return reviewService.getReviewsByUserId(userId);
    }

    @GetMapping("/getreviewsbyratinggreaterthan/{rating}")
    public List<ReviewDTO> getReviewsByRatingGreaterThan(@PathVariable int rating) {
        return reviewService.getReviewsByRatingGreaterThan(rating);
    }

    @GetMapping("/getreviewsbyratinglessthan/{rating}")
    public List<ReviewDTO> getReviewsByRatingLessThan(@PathVariable int rating) {
        return reviewService.getReviewsByRatingLessThan(rating);
    }

    @GetMapping("/getreviewsbyhotelandrating/{hotelId}/{rating}")
    public List<ReviewDTO> getReviewsByHotelAndRating(@PathVariable Long hotelId, @PathVariable int rating) {
        return reviewService.getReviewsByHotelAndRating(hotelId, rating);
    }

    @GetMapping("/getreviewsbyuserandrating/{userId}/{rating}")
    public List<ReviewDTO> getReviewsByUserAndRating(@PathVariable Long userId, @PathVariable int rating) {
        return reviewService.getReviewsByUserAndRating(userId, rating);
    }
    
    @GetMapping("/getreviewsbyhotelanduser/{hotelId}/{userId}")
    public List<ReviewDTO> getReviewsByHotelAndUser(@PathVariable Long hotelId, @PathVariable Long userId) {
        return reviewService.getReviewsByHotelAndUser(hotelId, userId);
    }
}
