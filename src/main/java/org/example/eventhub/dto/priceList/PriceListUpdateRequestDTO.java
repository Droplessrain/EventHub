package org.example.eventhub.dto.priceList;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.example.eventhub.model.enums.ServiceType;

public record PriceListUpdateRequestDTO(
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