package org.example.eventhub.dto.contractor;

import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record ContractorResponseDTO(
        String name,
        String lastName,
        LocalDate birthDate,
        Long userId,
        String description
) {
}
