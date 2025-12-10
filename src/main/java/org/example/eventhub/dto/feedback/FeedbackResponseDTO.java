package org.example.eventhub.dto.feedback;

import org.example.eventhub.model.entity.Contractor;
import org.example.eventhub.model.entity.User;
import org.example.eventhub.model.enums.FeedbackStatus;

import java.time.LocalDateTime;

public record FeedbackResponseDTO(
        LocalDateTime feedbackDateTime,
        String title,
        String description,
        User user,
        Contractor contractor,
        String rejectReason,
        FeedbackStatus feedbackStatus
) {
}
