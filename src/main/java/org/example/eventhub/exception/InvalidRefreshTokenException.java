package org.example.eventhub.exception;

public class InvalidRefreshTokenException extends RuntimeException{
    public InvalidRefreshTokenException(String message){
        super(message);
    }
}
