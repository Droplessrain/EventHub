package org.example.eventhub.mapper;

import org.example.eventhub.dto.priceList.PriceListCreateDTO;
import org.example.eventhub.dto.priceList.PriceListResponseDTO;
import org.example.eventhub.model.entity.PriceList;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE)
public interface PriceListMapper {
    @Mapping(target = "created_date", ignore = true)
    @Mapping(target = "updated_date", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "id", ignore = true)
    PriceList toEntity(PriceListResponseDTO priceListResponseDTO);

    @Mapping(target = "created_date", ignore = true)
    @Mapping(target = "updated_date", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "id", ignore = true)
    PriceList toEntity(PriceListCreateDTO priceListCreateDTO);

    PriceListResponseDTO toDTO(PriceList priceList);
}
