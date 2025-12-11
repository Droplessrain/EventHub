package org.example.eventhub.dto.contractor;

import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record ContractorCreateRequestDTO(
        String name,
        String lastName,
        @Past
        LocalDate birthDate,
        Long userId,
        String description
) {
}
