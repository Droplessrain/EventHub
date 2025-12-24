package org.example.eventhub.dto.complaint;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import org.example.eventhub.model.enums.ComplaintStatus;

import java.time.LocalDateTime;

public record ComplaintCreateRequestDTO(
        @NotNull(message = "Complaint date and time is required")
        @PastOrPresent(message = "Complaint date cannot be in the future")
        LocalDateTime complaintDateTime,

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

        @NotNull(message = "Complaint status is required")
        ComplaintStatus complaintStatus,

        @NotNull(message = "Reject reason status is required")
        String rejectReason
) {
}