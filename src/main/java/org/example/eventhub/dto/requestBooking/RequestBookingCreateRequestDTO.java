package org.example.eventhub.dto.requestBooking;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.example.eventhub.model.entity.Event;
import org.example.eventhub.model.enums.BookingStatus;

import java.time.LocalDateTime;

public record RequestBookingCreateRequestDTO(
        @NotNull(message = "Request date and time is required")
        LocalDateTime requestDateTime,

        @NotNull(message = "User ID is required")
        @Positive(message = "User ID must be positive")
        Long userId,

        @NotNull(message = "Contractor ID is required")
        @Positive(message = "Contractor ID must be positive")
        Long contractorId,

        @NotNull(message = "Event is required")
        Event event,

        @NotNull(message = "Booking status is required")
        BookingStatus bookingStatus,

        @NotNull(message = "Reject reason is required")
        String rejectReason,

        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Description is required")
        String description
) {
}