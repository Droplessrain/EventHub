package org.example.eventhub.dto.security;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginRequest(

        @NotNull(message = "username is required")
        @Size(min = 8, max = 20, message = "username must be between 8 and 20")
        String username,

        @NotNull(message = "password is required")
        @Size(min = 8, max = 20, message = "password must be between 8 and 20")
        String password) {}