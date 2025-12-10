package org.example.eventhub.dto.contractorEvent;

import org.example.eventhub.model.entity.Contractor;
import org.example.eventhub.model.entity.Event;
import org.example.eventhub.model.enums.ServiceType;

import java.time.LocalDateTime;

public record ContractorEventResponseDTO(
        Contractor contractor,
        Event event,
        LocalDateTime contractorEventDateTime,
        Short totalTime,
        Long cost,
        ServiceType serviceType,
        String description
) {
}
