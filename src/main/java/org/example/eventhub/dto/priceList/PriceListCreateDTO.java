package org.example.eventhub.dto.priceList;

import org.example.eventhub.model.entity.Contractor;
import org.example.eventhub.model.enums.ServiceType;

public record PriceListCreateDTO(
        Contractor contractor,
        ServiceType serviceType,
        Short quantityOfHours,
        Long price,
        String experience
) {
}
