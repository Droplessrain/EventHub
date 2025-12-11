package org.example.eventhub.dto.contractor;

import org.example.eventhub.model.entity.User;

import java.time.LocalDate;

public record ContractorResponseDTO(
        String name,
        String lastName,
        LocalDate birthDate,
        Long userId,
        String description
) {
}
