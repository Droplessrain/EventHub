package org.example.eventhub.exception.security;

public class UnauthorizedOperationException extends RuntimeException{
    public UnauthorizedOperationException(String message){
        super(message);
    }
}
