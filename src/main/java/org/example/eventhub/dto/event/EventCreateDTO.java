package org.example.eventhub.dto.event;

import org.example.eventhub.model.entity.User;
import org.example.eventhub.model.enums.EventType;

import java.time.LocalDateTime;

public record EventCreateDTO(
        User user,
        String title,
        EventType eventType,
        LocalDateTime eventDate,
        Short totalTime,
        String description
) {
}
