package org.example.eventhub.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.eventhub.model.enums.UserRole;

import java.time.LocalDateTime;

public record UserResponseDTO (
        @NotNull(message = "id is required")
        Long id,
        @Size(min = 3, message = "username must be more 3")
        String username,
        @Size(min = 8, max = 20, message = "password must be between 8 and 20")
        String password,
        @NotNull(message = "role is required")
        UserRole role,
        @Email
        @NotNull
        String email){}
