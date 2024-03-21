package com.hexaware.CozyHeavenStay.customexception;

public class AlreadyBookedException extends RuntimeException {

    public AlreadyBookedException() {
        super();
    }

    public AlreadyBookedException(String message) {
        super(message);
    }

    public AlreadyBookedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyBookedException(Throwable cause) {
        super(cause);
    }
}
