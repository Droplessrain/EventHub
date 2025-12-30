package org.example.eventhub.exception.user;

public class InvalidPasswordForUsernameException extends RuntimeException {
  public InvalidPasswordForUsernameException(String message) {
    super(message);
  }
}
