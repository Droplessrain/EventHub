package org.example.eventhub.dto.priceList;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.example.eventhub.model.enums.ServiceType;

public record PriceListCreateRequestDTO(
        @NotNull(message = "User ID is required")
        @Positive(message = "User ID must be positive")
        Long userId,

        @NotNull(message = "Contractor ID is required")
        @Positive(message = "Contractor ID must be positive")
        Long contractorId,

        @NotNull(message = "Service type is required")
        ServiceType serviceType,

        @NotNull(message = "Quantity of hours is required")
        @Positive(message = "Quantity of hours must be positive")
        Short quantityOfHours,

        @NotNull(message = "Price is required")
        @PositiveOrZero(message = "Price must be zero or positive")
        Long price,

        @NotBlank(message = "Experience is required")
        String experience
) {
}