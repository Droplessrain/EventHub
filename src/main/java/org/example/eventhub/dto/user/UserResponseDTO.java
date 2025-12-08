package org.example.eventhub.dto.user;

import org.example.eventhub.model.enums.UserRole;

public record UserResponseDTO (
        Long id,
        String username,
        String password,
        UserRole role,
        String email){}
