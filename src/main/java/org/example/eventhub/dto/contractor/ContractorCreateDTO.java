package org.example.eventhub.dto.contractor;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.example.eventhub.model.entity.User;

import java.time.LocalDate;

public record ContractorCreateDTO(
        String name,
        String lastName,
        LocalDate birthDate,
        User user,
        String description
) {
}
