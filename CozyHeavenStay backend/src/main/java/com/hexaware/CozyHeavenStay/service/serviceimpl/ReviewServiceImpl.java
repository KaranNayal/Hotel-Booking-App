package com.hexaware.CozyHeavenStay.service.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.CozyHeavenStay.dto.ReviewDTO;
import com.hexaware.CozyHeavenStay.entities.Review;
import com.hexaware.CozyHeavenStay.repository.ReviewRepository;
import com.hexaware.CozyHeavenStay.service.ReviewService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReviewDTO saveReview(ReviewDTO reviewDTO) {
        Review review = modelMapper.map(reviewDTO, Review.class);
        return modelMapper.map(reviewRepository.save(review), ReviewDTO.class);
    }

    @Override
    public ReviewDTO getReviewById(Long id) {
        Review review = reviewRepository.findById(id).orElse(null);
        return modelMapper.map(review, ReviewDTO.class);
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream()
                .map(review -> modelMapper.map(review, ReviewDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> getReviewsByHotelId(Long hotelId) {
        List<Review> reviews = reviewRepository.findByHotelId(hotelId);
        return reviews.stream()
                .map(review -> modelMapper.map(review, ReviewDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> getReviewsByUserId(Long userId) {
        List<Review> reviews = reviewRepository.findByUserId(userId);
        return reviews.stream()
                .map(review -> modelMapper.map(review, ReviewDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> getReviewsByRatingGreaterThan(int rating) {
        List<Review> reviews = reviewRepository.findByRatingGreaterThan(rating);
        return reviews.stream()
                .map(review -> modelMapper.map(review, ReviewDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> getReviewsByRatingLessThan(int rating) {
        List<Review> reviews = reviewRepository.findByRatingLessThan(rating);
        return reviews.stream()
                .map(review -> modelMapper.map(review, ReviewDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> getReviewsByHotelAndRating(Long hotelId, int rating) {
        List<Review> reviews = reviewRepository.findByHotelIdAndRating(hotelId, rating);
        return reviews.stream()
                .map(review -> modelMapper.map(review, ReviewDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> getReviewsByUserAndRating(Long userId, int rating) {
        List<Review> reviews = reviewRepository.findByUserIdAndRating(userId, rating);
        return reviews.stream()
                .map(review -> modelMapper.map(review, ReviewDTO.class))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<ReviewDTO> getReviewsByHotelAndUser(Long hotelId, Long userId) {
        List<Review> reviews = reviewRepository.findByHotelIdAndUserId(hotelId, userId);
        return reviews.stream()
                .map(review -> modelMapper.map(review, ReviewDTO.class))
                .collect(Collectors.toList());
    }
}
