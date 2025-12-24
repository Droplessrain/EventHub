package org.example.eventhub.dto.contractorEvent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.example.eventhub.model.enums.ServiceType;

import java.time.LocalDateTime;

public record ContractorEventUpdateRequestDTO(
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