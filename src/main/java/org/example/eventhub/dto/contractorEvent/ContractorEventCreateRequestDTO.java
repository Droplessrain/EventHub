package org.example.eventhub.dto.contractorEvent;

import org.example.eventhub.model.entity.Contractor;
import org.example.eventhub.model.entity.Event;
import org.example.eventhub.model.enums.ServiceType;

import java.time.LocalDateTime;

public record ContractorEventCreateRequestDTO(
        Contractor contractor,
        Long eventId,
        LocalDateTime contractorEventDateTime,
        Short totalTime,
        Long cost,
        ServiceType serviceType,
        String description
) {
}
