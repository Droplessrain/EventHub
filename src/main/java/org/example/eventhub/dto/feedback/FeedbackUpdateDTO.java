package org.example.eventhub.dto.feedback;

import org.example.eventhub.model.enums.FeedbackStatus;

public record FeedbackUpdateDTO(
        String title,
        String description,
        String rejectReason,
        FeedbackStatus feedbackStatus
) {
}
