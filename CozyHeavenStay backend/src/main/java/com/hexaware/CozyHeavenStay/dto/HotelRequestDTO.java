package com.hexaware.CozyHeavenStay.dto;
public class HotelRequestDTO {
    private HotelDTO hotelDTO;
    private HotelOwnerDTO ownerDTO;

    // Getters and setters
    public HotelDTO getHotelDTO() {
        return hotelDTO;
    }

    public void setHotelDTO(HotelDTO hotelDTO) {
        this.hotelDTO = hotelDTO;
    }

    public HotelOwnerDTO getOwnerDTO() {
        return ownerDTO;
    }

    public void setOwnerDTO(HotelOwnerDTO ownerDTO) {
        this.ownerDTO = ownerDTO;
    }
}
