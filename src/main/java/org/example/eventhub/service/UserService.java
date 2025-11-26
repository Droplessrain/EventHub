package org.example.eventhub.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.eventhub.dto.user.UserCreateDTO;
import org.example.eventhub.dto.user.UserResponseDTO;
import org.example.eventhub.dto.user.UserUpdateDTO;
import org.example.eventhub.filterEntity.SearchUsersFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service interface for managing user operations in the banking system.
 * Provides CRUD operations and additional user-related functionality.
 */
@Service
public interface UserService {

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id the unique identifier of the user (must not be null)
     * @return UserResponseDTO containing user details
     * @throws IllegalArgumentException if id is null
     * @throws EntityNotFoundException if user with specified id does not exist
     */
    UserResponseDTO findById(Long id);

    /**
     * Retrieves all users in the system.
     *
     * @param searchUsersFilter filter for search
     * @param pageable ex of page
     * @return page of UserResponseDTO objects (never null, may be empty)
     */
    Page<UserResponseDTO> getUsers(SearchUsersFilter searchUsersFilter, Pageable pageable);

    /**
     * Updates an existing user's information.
     *
     * @param id the unique identifier of the user to update (must not be null)
     * @param userUpdateDTO DTO containing updated user information (must not be null)
     * @return UserResponseDTO with updated user details
     * @throws IllegalArgumentException if id is null or userUpdateDTO is null
     * @throws EntityNotFoundException if user with specified id does not exist
     */
    UserResponseDTO updateUser(Long id, UserUpdateDTO userUpdateDTO);

    /**
     * Creates a new user in the system.
     *
     * @param userCreateDTO DTO containing user information for creation (must not be null)
     * @return UserResponseDTO with created user details including generated identifier
     * @throws IllegalArgumentException if userCreateDTO is null or contains invalid data
     */
    UserResponseDTO createUser(UserCreateDTO userCreateDTO);

    /**
     * Deletes a user by their unique identifier.
     *
     * @param id the unique identifier of the user to delete (must not be null)
     * @throws IllegalArgumentException if id is null or user does not exist
     */
    void deleteById(Long id);
}