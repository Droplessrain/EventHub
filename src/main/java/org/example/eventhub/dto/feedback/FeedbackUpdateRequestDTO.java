package org.example.eventhub.dto.feedback;

import org.example.eventhub.model.enums.FeedbackStatus;

public record FeedbackUpdateRequestDTO(
        String title,
        String description,
        String rejectReason,
        FeedbackStatus feedbackStatus
) {
}
