package org.example.eventhub.dto.priceList;

import org.example.eventhub.model.enums.ServiceType;

public record PriceListUpdateRequestDTO(
        ServiceType serviceType,
        Short quantityOfHours,
        Long price,
        String experience
) {
}
