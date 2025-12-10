package org.example.eventhub.dto.complaint;

import jakarta.validation.constraints.Size;
import org.example.eventhub.model.entity.Contractor;
import org.example.eventhub.model.entity.User;
import org.example.eventhub.model.enums.ComplaintStatus;

import java.time.LocalDateTime;

public record ComplaintUpdateDTO (
        LocalDateTime complaintDateTime,
        @Size(max = 80)
        String title,
        String description,
        ComplaintStatus complaintStatus,
        String rejectReason
){
}
