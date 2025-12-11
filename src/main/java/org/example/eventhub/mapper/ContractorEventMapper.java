package org.example.eventhub.mapper;

import org.example.eventhub.dto.contractorEvent.ContractorEventCreateRequestDTO;
import org.example.eventhub.dto.contractorEvent.ContractorEventResponseDTO;
import org.example.eventhub.model.entity.ContractorEvent;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE)
public interface ContractorEventMapper {
    @Mapping(target = "created_date", ignore = true)
    @Mapping(target = "updated_date", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "event.id", source = "eventId")
    ContractorEvent toEntity(ContractorEventResponseDTO contractorEventResponseDTO);

    @Mapping(target = "created_date", ignore = true)
    @Mapping(target = "updated_date", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "event.id", source = "eventId")
    ContractorEvent toEntity(ContractorEventCreateRequestDTO contractorEventCreateRequestDTO);

    @Mapping(source = "event.id", target = "eventId")
    ContractorEventResponseDTO toDTO(ContractorEvent contractorEvent);

}
