package org.example.eventhub.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.eventhub.dto.user.UserCreateRequestDTO;
import org.example.eventhub.dto.user.UserResponseDTO;
import org.example.eventhub.dto.user.UserUpdateRequestDTO;
import org.example.eventhub.exception.user.UserNotFoundException;
import org.example.eventhub.exception.user.UserWithThisEmailAlreadyExist;
import org.example.eventhub.exception.user.UserWithThisUsernameAlreadyExist;
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

import static org.example.eventhub.exception.ErrorConstants.USER_BY_ID_NOT_FOUND;
import static org.example.eventhub.exception.ErrorConstants.USER_WITH_THIS_EMAIL_ALREADY_EXIST;
import static org.example.eventhub.exception.ErrorConstants.USER_WITH_THIS_USERNAME_ALREADY_EXIST;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(()-> new UserNotFoundException(String.format(USER_BY_ID_NOT_FOUND, id)));
    }

    @Override
    public Page<UserResponseDTO> getUsers(SearchUsersFilter searchUsersFilter, Pageable pageable) {
        Specification<User> specification = UserSpecifications.withFilters(searchUsersFilter);
        Page<User> users = userRepository.findAll(specification, pageable);
        return users.map(userMapper::toDTO);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserUpdateRequestDTO userUpdateDTO) {
        User user = userRepository
                .findById(id)
                .orElseThrow(()-> new UserNotFoundException(String.format(USER_BY_ID_NOT_FOUND, id)));

        User userSaved = userRepository.save(user);
        return userMapper.toDTO(userSaved);
    }

    @Override
    public UserResponseDTO createUser(UserCreateRequestDTO userCreateDTO) {
        if(userRepository.findByUsername(userCreateDTO.username()).isPresent()) {
            throw new UserWithThisUsernameAlreadyExist(USER_WITH_THIS_USERNAME_ALREADY_EXIST);
        }
        if(userRepository.findByEmail(userCreateDTO.email()).isPresent()) {
            throw new UserWithThisEmailAlreadyExist(USER_WITH_THIS_EMAIL_ALREADY_EXIST);
        }
        User userCreated = userMapper.toEntity(userCreateDTO);
        User userSaved = userRepository.save(userCreated);
        return userMapper.toDTO(userSaved);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
