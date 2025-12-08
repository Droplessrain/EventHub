package org.example.eventhub.exception;

public class UserWithThisUsernameAlreadyExist extends RuntimeException {
    public UserWithThisUsernameAlreadyExist(String message) {
        super(message);
    }
}
