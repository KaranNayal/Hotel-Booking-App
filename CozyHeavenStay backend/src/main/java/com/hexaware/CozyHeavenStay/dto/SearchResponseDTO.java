package com.hexaware.CozyHeavenStay.dto;

import java.util.List;

public class SearchResponseDTO {
    private List<HotelDTO> hotels;

    public SearchResponseDTO() {
    }

    public List<HotelDTO> getHotels() {
        return hotels;
    }

    public void setHotels(List<HotelDTO> hotels) {
        this.hotels = hotels;
    }

	@Override
	public String toString() {
		return "SearchResponseDTO [hotels=" + hotels + "]";
	}
}
