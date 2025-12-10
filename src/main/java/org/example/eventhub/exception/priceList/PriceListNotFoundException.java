package org.example.eventhub.exception.priceList;

public class PriceListNotFoundException extends RuntimeException {
    public PriceListNotFoundException(String message) {
        super(message);
    }
}
