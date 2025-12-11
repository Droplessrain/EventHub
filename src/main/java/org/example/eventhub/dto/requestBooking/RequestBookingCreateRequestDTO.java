package org.example.eventhub.dto.requestBooking;


import org.example.eventhub.model.entity.Contractor;
import org.example.eventhub.model.entity.Event;
import org.example.eventhub.model.entity.User;
import org.example.eventhub.model.enums.BookingStatus;

import java.time.LocalDateTime;

public record RequestBookingCreateRequestDTO(
        LocalDateTime requestDateTime,
        User user,
        Contractor contractor,
        Event event,
        BookingStatus bookingStatus,
        String rejectReason,
        String title,
        String description
) {
}
