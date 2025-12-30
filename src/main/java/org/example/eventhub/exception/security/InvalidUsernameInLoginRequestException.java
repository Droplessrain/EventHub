package org.example.eventhub.exception.security;

public class InvalidUsernameInLoginRequestException extends RuntimeException {
    public InvalidUsernameInLoginRequestException(String message) {
        super(message);
    }
}
