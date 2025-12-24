package org.example.eventhub.mapper;

import org.example.eventhub.dto.event.EventCreateRequestDTO;
import org.example.eventhub.dto.event.EventResponseDTO;
import org.example.eventhub.model.entity.Event;
import org.example.eventhub.service.UserService;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE)
public interface EventMapper {

    @Mapping(target = "created_date", ignore = true)
    @Mapping(target = "updated_date", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId.id", source = "userId")
    Event toEntity(EventResponseDTO eventResponseDTO);

    @Mapping(target = "created_date", ignore = true)
    @Mapping(target = "updated_date", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId.id", source = "userId")
    Event toEntity(EventCreateRequestDTO eventCreateDTO);

    @Mapping(source = "userId.id", target = "userId")
    EventResponseDTO toDto(Event event);
}
