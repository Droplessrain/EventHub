package org.example.eventhub.mapper;

import org.example.eventhub.dto.requestBooking.RequestBookingCreateRequestDTO;
import org.example.eventhub.dto.requestBooking.RequestBookingResponseDTO;
import org.example.eventhub.model.entity.RequestBooking;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE)
public interface RequestBookingMapper {
    @Mapping(target = "created_date", ignore = true)
    @Mapping(target = "updated_date", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contractor.id", source = "contractorId")
    @Mapping(target = "user.id", source = "userId")
    RequestBooking toEntity(RequestBookingResponseDTO requestBookingResponseDTO);

    @Mapping(target = "created_date", ignore = true)
    @Mapping(target = "updated_date", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contractor.id", source = "contractorId")
    @Mapping(target = "user.id", source = "userId")
    RequestBooking toEntity(RequestBookingCreateRequestDTO requestBookingCreateDTO);

    @Mapping(source = "contractor.id", target = "contractorId")
    @Mapping(source = "user.id", target = "userId")
    RequestBookingResponseDTO toDto(RequestBooking requestBooking);

}
