package org.example.eventhub.dto.feedback;

import org.example.eventhub.model.enums.FeedbackStatus;

import java.time.LocalDateTime;

public record FeedbackResponseDTO(
        LocalDateTime feedbackDateTime,
        String title,
        String description,
        Long userId,
        Long contractorId,
        String rejectReason,
        FeedbackStatus feedbackStatus
) {
}
