package org.example.eventhub.mapper;

import org.example.eventhub.dto.contractor.ContractorCreateRequestDTO;
import org.example.eventhub.dto.contractor.ContractorResponseDTO;
import org.example.eventhub.model.entity.Contractor;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE)
public interface ContractorMapper {

    @Mapping(target = "created_date", ignore = true)
    @Mapping(target = "updated_date", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "id", ignore = true)
    Contractor toEntity(ContractorResponseDTO responseDTO);

    @Mapping(target = "created_date", ignore = true)
    @Mapping(target = "updated_date", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "id", ignore = true)
    Contractor toEntity(ContractorCreateRequestDTO createDTO);

    ContractorResponseDTO toDTO(Contractor entity);
}
