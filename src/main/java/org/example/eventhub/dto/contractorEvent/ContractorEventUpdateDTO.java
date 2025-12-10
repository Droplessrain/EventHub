package org.example.eventhub.dto.contractorEvent;

import org.example.eventhub.model.enums.ServiceType;

import java.time.LocalDateTime;

public record ContractorEventUpdateDTO(
        LocalDateTime contractorEventDateTime,
        Short totalTime,
        Long cost,
        ServiceType serviceType,
        String description
) {
}
