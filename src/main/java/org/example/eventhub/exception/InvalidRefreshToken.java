package org.example.eventhub.exception;

public class InvalidRefreshToken extends RuntimeException{
    public InvalidRefreshToken(String message){
        super(message);
    }
}
