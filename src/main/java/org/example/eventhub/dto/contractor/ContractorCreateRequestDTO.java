package org.example.eventhub.dto.contractor;

import java.time.LocalDate;

public record ContractorCreateRequestDTO(
        String name,
        String lastName,
        LocalDate birthDate,
        Long userId,
        String description
) {
}
