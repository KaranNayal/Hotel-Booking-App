package com.hexaware.CozyHeavenStay.dto;

public class ReviewDTO {
    private Long reviewId;
    private Long hotelId;
    private Long userId;
    private String comment;
    private int rating;

    // Getters and setters
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

    // Override toString() if needed
    @Override
    public String toString() {
        return "ReviewDTO [reviewId=" + reviewId + ", hotelId=" + hotelId + ", userId=" + userId + ", comment=" + comment
                + ", rating=" + rating + "]";
    }
}
