package org.example.eventhub.exception;

public class UserWithThisEmailAlreadyExist extends RuntimeException {
    public UserWithThisEmailAlreadyExist(String message) {
        super(message);
    }
}
