package org.example.eventhub.dto.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.example.eventhub.model.enums.EventType;

import java.time.LocalDateTime;

public record EventUpdateRequestDTO(
        @NotBlank(message = "Title is required")
        String title,

        @NotNull(message = "Event type is required")
        EventType eventType,

        @NotNull(message = "Event date is required")
        LocalDateTime eventDate,

        @NotNull(message = "Total time is required")
        @Positive(message = "Total time must be positive")
        Short totalTime,

        @NotBlank(message = "Description is required")
        String description
) {
}