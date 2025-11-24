package org.example.eventhub.exception;

public final class ErrorConstants {
    private ErrorConstants() {}

    public static final String CARD_NOT_FOUND = "Card not found with ID: %s";
    public static final String NOT_CARD_OWNER = "You don't own the source card";
    public static final String INSUFFICIENT_FUNDS = "Insufficient funds on source card";
    public static final String CARD_NOT_ACTIVE = "Card with ID: %s is not active";
}