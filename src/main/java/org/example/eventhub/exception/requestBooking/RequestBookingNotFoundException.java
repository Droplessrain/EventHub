package org.example.eventhub.exception.requestBooking;

public class RequestBookingNotFoundException extends RuntimeException {
    public RequestBookingNotFoundException(String message) {
        super(message);
    }
}
