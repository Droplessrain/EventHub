package org.example.eventhub.dto.feedback;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.eventhub.model.enums.FeedbackStatus;

public record FeedbackUpdateRequestDTO(
        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Description is required")
        String description,

        @NotNull(message = "Reject reason ID is required")
        String rejectReason,

        @NotNull(message = "Feedback status is required")
        FeedbackStatus feedbackStatus
) {
}