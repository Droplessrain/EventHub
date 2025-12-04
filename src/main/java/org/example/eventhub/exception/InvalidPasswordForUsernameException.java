package org.example.eventhub.exception;

public class InvalidPasswordForUsernameException extends RuntimeException {
  public InvalidPasswordForUsernameException(String message) {
    super(message);
  }
}
