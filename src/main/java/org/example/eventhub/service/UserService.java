package org.example.eventhub.service;

import org.example.eventhub.dto.user.UserCreateRequestDTO;
import org.example.eventhub.dto.user.UserResponseDTO;
import org.example.eventhub.dto.user.UserUpdateRequestDTO;
import org.example.eventhub.filterEntity.SearchUsersFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserResponseDTO findById(Long id);

    Page<UserResponseDTO> getUsers(SearchUsersFilter searchUsersFilter, Pageable pageable);

    UserResponseDTO updateUser(Long id, UserUpdateRequestDTO userUpdateDTO);

    UserResponseDTO createUser(UserCreateRequestDTO userCreateDTO);

    void deleteById(Long id);
}