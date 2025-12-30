package org.example.eventhub.dto.feedback;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import org.example.eventhub.model.enums.FeedbackStatus;

import java.time.LocalDateTime;

public record FeedbackCreateRequestDTO(
        @NotNull(message = "Feedback date and time is required")
        @PastOrPresent(message = "Feedback date cannot be in the future")
        LocalDateTime feedbackDateTime,

        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Description is required")
        String description,

        @NotNull(message = "User ID is required")
        @Positive(message = "User ID must be positive")
        Long userId,

        @NotNull(message = "Contractor ID is required")
        @Positive(message = "Contractor ID must be positive")
        Long contractorId,

        @NotNull(message = "Reject reason ID is required")
        String rejectReason,

        @NotNull(message = "Feedback status is required")
        FeedbackStatus feedbackStatus
) {
}