package com.hexaware.CozyHeavenStay.entities;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long reviewId;
	   @NotNull(message = "Hotel ID is required")
	    private Long hotelId;
	    
	    @NotNull(message = "User ID is required")
	    private Long userId;
	    
	    @NotBlank(message = "Comment is required")
	    private String comment;
	    
	    @Min(value = 1, message = "Rating should be between 1 and 5")
	    @Max(value = 5, message = "Rating should be between 1 and 5")
	    private int rating;
	    
	@ManyToOne
    @JoinColumn()
    private Hotel hotel;

    @ManyToOne
    @JoinColumn()
    private User user;

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(Long reviewId, Long hotelId, Long userId, String comment, int rating, Hotel hotel, User user) {
		super();
		this.reviewId = reviewId;
		this.hotelId = hotelId;
		this.userId = userId;
		this.comment = comment;
		this.rating = rating;
		this.hotel = hotel;
		this.user = user;
	}

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", hotelId=" + hotelId + ", userId=" + userId + ", comment=" + comment
				+ ", rating=" + rating + ", hotel=" + hotel + ", user=" + user + "]";
	}
	

}
