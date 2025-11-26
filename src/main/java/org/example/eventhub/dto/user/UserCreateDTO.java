package org.example.eventhub.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.eventhub.model.enums.UserRole;

public record UserCreateDTO(
        @NotNull(message = "username is required")
        @Size(min = 3, message = "username must be more 3")
        String username,
        @NotNull(message = "password is required")
        @Size(min = 8, max = 20, message = "password must be between 8 and 20")
        String password,
        @Email
        @NotNull(message = "email is required")
        String email,
        @NotNull(message = "role is required")
        UserRole role){}
