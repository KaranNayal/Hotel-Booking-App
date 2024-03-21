package com.hexaware.CozyHeavenStay.customexception;

public class HotelOwnerNotFoundException extends RuntimeException {
    public HotelOwnerNotFoundException(String message) {
        super(message);
    }
}
