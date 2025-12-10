package org.example.eventhub.dto.contractorEvent;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.example.eventhub.model.entity.Contractor;
import org.example.eventhub.model.entity.Event;
import org.example.eventhub.model.enums.ServiceType;

import java.time.LocalDateTime;

public record ContractorEventCreateDTO(
        Contractor contractor,
        Event event,
        LocalDateTime contractorEventDateTime,
        Short totalTime,
        Long cost,
        ServiceType serviceType,
        String description
) {
}
