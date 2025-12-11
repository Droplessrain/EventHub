package org.example.eventhub.dto.requestBooking;

import org.example.eventhub.model.enums.BookingStatus;

import java.time.LocalDateTime;

public record RequestBookingUpdateRequestDTO(
        LocalDateTime requestDateTime,
        BookingStatus bookingStatus,
        String rejectReason,
        String title,
        String description
) {
}
