package org.example.eventhub.exception;

public class InvalidUsernameInLoginRequestException extends RuntimeException {
    public InvalidUsernameInLoginRequestException(String message) {
        super(message);
    }
}
