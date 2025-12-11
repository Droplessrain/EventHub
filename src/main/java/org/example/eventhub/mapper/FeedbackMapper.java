package org.example.eventhub.mapper;

import org.example.eventhub.dto.feedback.FeedbackCreateRequestDTO;
import org.example.eventhub.dto.feedback.FeedbackResponseDTO;
import org.example.eventhub.model.entity.Feedback;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE)
public interface FeedbackMapper {
    @Mapping(target = "created_date", ignore = true)
    @Mapping(target = "updated_date", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contractor.id", source = "contractorId")
    @Mapping(target = "user.id", source = "userId")
    Feedback toEntity(FeedbackResponseDTO feedbackResponseDTO);

    @Mapping(target = "created_date", ignore = true)
    @Mapping(target = "updated_date", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contractor.id", source = "contractorId")
    @Mapping(target = "user.id", source = "userId")
    Feedback toEntity(FeedbackCreateRequestDTO feedbackCreateDTO);

    @Mapping(source = "contractor.id", target = "contractorId")
    @Mapping(source = "user.id", target = "userId")
    FeedbackResponseDTO toDTO(Feedback feedback);
}
