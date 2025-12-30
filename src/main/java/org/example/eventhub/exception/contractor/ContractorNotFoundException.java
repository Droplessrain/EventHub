package org.example.eventhub.exception.contractor;

public class ContractorNotFoundException extends RuntimeException {
    public ContractorNotFoundException(String message) {
        super(message);
    }
}
