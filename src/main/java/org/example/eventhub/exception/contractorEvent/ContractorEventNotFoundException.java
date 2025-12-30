package org.example.eventhub.exception.contractorEvent;

public class ContractorEventNotFoundException extends RuntimeException {
    public ContractorEventNotFoundException(String message) {
        super(message);
    }
}
