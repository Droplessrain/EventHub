package org.example.eventhub.dto.priceList;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.example.eventhub.model.entity.Contractor;
import org.example.eventhub.model.enums.ServiceType;

public record PriceListResponseDTO(
        Contractor contractor,
        ServiceType serviceType,
        Short quantityOfHours,
        Long price,
        String experience
) {
}
