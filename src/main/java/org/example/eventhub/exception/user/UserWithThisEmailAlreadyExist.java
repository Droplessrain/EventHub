package org.example.eventhub.exception.user;

public class UserWithThisEmailAlreadyExist extends RuntimeException {
    public UserWithThisEmailAlreadyExist(String message) {
        super(message);
    }
}
