package org.example.eventhub.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.eventhub.dto.user.UserCreateDTO;
import org.example.eventhub.dto.user.UserResponseDTO;
import org.example.eventhub.dto.user.UserUpdateDTO;
import org.example.eventhub.exception.UserNotFoundException;
import org.example.eventhub.filterEntity.SearchUsersFilter;
import org.example.eventhub.mapper.UserMapper;
import org.example.eventhub.model.entity.User;
import org.example.eventhub.repository.UserRepository;
import org.example.eventhub.service.UserService;
import org.example.eventhub.specifications.UserSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO findById(Long id) {
        return userMapper.toDTO(userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("Not found user with id: " + id)));
    }

    @Override
    public Page<UserResponseDTO> getUsers(SearchUsersFilter searchUsersFilter, Pageable pageable) {
        Specification<User> specification = UserSpecifications.withFilters(searchUsersFilter);
        Page<User> users = userRepository.findAll(specification, pageable);
        return users.map(userMapper::toDTO);
    }

    @Override
    @Transactional
    public UserResponseDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        User userFirst = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("user for update not found"));
        return userMapper.toDTO(userRepository.save(userFirst));
    }

    @Override
    public UserResponseDTO createUser(UserCreateDTO userCreateDTO) {
        User createdUser = userMapper.toEntity(userCreateDTO);
        return userMapper.toDTO(userRepository.save(createdUser));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean isCurrentUserCardOwner(Long userId) {
        return false;
    }
}
