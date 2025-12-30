package org.example.eventhub.dto.complaint;

import org.example.eventhub.model.entity.Contractor;
import org.example.eventhub.model.enums.ComplaintStatus;

import java.time.LocalDateTime;

public record ComplaintResponseDTO (
        LocalDateTime complaintDateTime,
        String title,
        String description,
        Long userId,
        Long contractorId,
        ComplaintStatus complaintStatus,
        String rejectReason
){}