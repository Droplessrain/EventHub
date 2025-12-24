package org.example.eventhub.dto.contractorEvent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.example.eventhub.model.entity.Contractor;
import org.example.eventhub.model.enums.ServiceType;

import java.time.LocalDateTime;

public record ContractorEventCreateRequestDTO(
        @NotNull(message = "Contractor is required")
        Contractor contractor,

        @NotNull(message = "Event ID is required")
        @Positive(message = "Event ID must be positive")
        Long eventId,

        @NotNull(message = "Contractor event date and time is required")
        LocalDateTime contractorEventDateTime,

        @NotNull(message = "Total time is required")
        @Positive(message = "Total time must be positive")
        Short totalTime,

        @NotNull(message = "Cost is required")
        @PositiveOrZero(message = "Cost must be zero or positive")
        Long cost,

        @NotNull(message = "Service type is required")
        ServiceType serviceType,

        @NotBlank(message = "Description is required")
        String description
) {
}