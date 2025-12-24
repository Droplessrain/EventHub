package org.example.eventhub.mapper;

import org.example.eventhub.dto.complaint.ComplaintCreateRequestDTO;
import org.example.eventhub.dto.complaint.ComplaintResponseDTO;
import org.example.eventhub.model.entity.Complaint;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE)
public interface ComplaintMapper {

    @Mapping(target = "created_date", ignore = true)
    @Mapping(target = "updated_date", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "id", ignore = true)
    Complaint toEntity(ComplaintResponseDTO complaintResponseDTO);

    @Mapping(target = "created_date", ignore = true)
    @Mapping(target = "updated_date", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId.id", source = "userId")
    Complaint toEntity(ComplaintCreateRequestDTO complaintCreateRequestDTO);

    ComplaintResponseDTO toDto(Complaint complaint);
}
