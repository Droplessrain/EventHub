package org.example.eventhub.dto.contractor;

import jakarta.validation.constraints.NotBlank;

public record ContractorUpdateRequestDTO(
        @NotBlank(message = "Description is required")
        String description
) {
}
