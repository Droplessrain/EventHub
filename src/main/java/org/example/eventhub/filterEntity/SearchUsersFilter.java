package org.example.eventhub.filterEntity;


import org.example.eventhub.model.enums.UserRole;

public record SearchUsersFilter (
        String username,
        UserRole role)
{}
