package org.example.eventhub.dto.complaint;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.example.eventhub.model.enums.ComplaintStatus;

import java.time.LocalDateTime;

public record ComplaintUpdateRequestDTO(
        @NotNull(message = "Complaint date and time is required")
        @PastOrPresent(message = "Complaint date cannot be in the future")
        LocalDateTime complaintDateTime,

        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Description is required")
        String description,

        @NotNull(message = "Complaint status is required")
        ComplaintStatus complaintStatus,

        @NotNull(message = "Reject reason status is required")
        String rejectReason
){
}