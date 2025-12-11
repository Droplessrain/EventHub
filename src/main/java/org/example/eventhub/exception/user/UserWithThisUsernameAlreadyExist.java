package org.example.eventhub.exception.user;

public class UserWithThisUsernameAlreadyExist extends RuntimeException {
    public UserWithThisUsernameAlreadyExist(String message) {
        super(message);
    }
}
