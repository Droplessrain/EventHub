package org.example.eventhub.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserUpdateRequestDTO(
        @Size(min = 3, message = "username must be more 3")
        String username,
        @Size(min = 8, max = 20, message = "password must be between 8 and 20")
        String password,
        @Email
        @NotNull
        String email){}
