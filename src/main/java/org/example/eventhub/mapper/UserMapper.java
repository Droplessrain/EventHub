package org.example.eventhub.mapper;

import org.example.eventhub.dto.security.LoginRequest;
import org.example.eventhub.dto.user.UserCreateDTO;
import org.example.eventhub.dto.user.UserResponseDTO;
import org.example.eventhub.model.entity.User;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE)
public interface UserMapper {

    @Mapping(target = "created_date", ignore = true)
    @Mapping(target = "updated_date", ignore = true)
    User toEntity(UserResponseDTO userResponseDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_date", ignore = true)
    @Mapping(target = "updated_date", ignore = true)
    User toEntity(UserCreateDTO userCreateDTO);

    @Mapping(target = "created_date", ignore = true)
    @Mapping(target = "updated_date", ignore = true)
    List<User> toEntity(List<UserResponseDTO> userResponseDTO);

    LoginRequest toLoginRequest(UserResponseDTO userResponseDTO);
    
    UserResponseDTO toDTO(User user);

    List<UserResponseDTO> toDTO(List<User> user);
}
