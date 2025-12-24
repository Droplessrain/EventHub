package org.example.eventhub.dto.requestBooking;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.eventhub.model.enums.BookingStatus;

import java.time.LocalDateTime;

public record RequestBookingUpdateRequestDTO(
        @NotNull(message = "Request date and time is required")
        LocalDateTime requestDateTime,

        @NotNull(message = "Booking status is required")
        BookingStatus bookingStatus,

        @NotNull(message = "Reject reason status is required")
        String rejectReason,

        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Description is required")
        String description
) {
}