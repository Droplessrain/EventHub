package org.example.eventhub.auth;

import org.example.eventhub.dto.user.UserCreateDTO;
import org.example.eventhub.dto.user.UserResponseDTO;
import org.example.eventhub.dto.user.UserUpdateDTO;
import org.example.eventhub.filterEntity.SearchUsersFilter;
import org.example.eventhub.mapper.UserMapper;
import org.example.eventhub.model.entity.User;
import org.example.eventhub.model.enums.UserRole;
import org.example.eventhub.repository.UserRepository;
import org.example.eventhub.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private Long userId;
    private UserResponseDTO response;
    private UserResponseDTO updateResponse;
    private SearchUsersFilter searchUsersFilter;
    private UserUpdateDTO updateDTO;
    private UserCreateDTO createDTO;
    private List<User> userList;
    private Page<User> userPage;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        user = new User(
                userId,
                "username",
                "testpassword",
                "testemail@mail.com",
                UserRole.USER,
                null,
                null);
        userId = 1L;
        response = new UserResponseDTO(
                userId,
                "username",
                "testpassword",
                UserRole.USER,
                "testemail@mail.com"
        );

        updateResponse = new UserResponseDTO(
                userId,
                "username1",
                "testpassword",
                UserRole.USER,
                "testemail1@mail.com");

        searchUsersFilter = new SearchUsersFilter("username", UserRole.USER);
        updateDTO = new UserUpdateDTO("username1", "testpassword", "testemail1@mail.com");
        createDTO = new UserCreateDTO("username", "testpassword", "testemail@mail.com", UserRole.USER);
        userList = List.of(user);
        pageable = Pageable.ofSize(1);
        userPage = new PageImpl<User>(userList, pageable, 1L) ;

    }

    @Test
    void findById_withValidId_returnsUser() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.toDTO(user)).thenReturn(response);

        UserResponseDTO responseDTO = userService.findById(userId);

        verify(userRepository).findById(userId);
        verify(userMapper).toDTO(user);

        assertThat(responseDTO).isEqualTo(response);
    }

    @Test
    void getUsers_withValidData_returnsUsers() {
        when(userMapper.toDTO(user)).thenReturn(response);
        when(userRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(userPage);

        Page<UserResponseDTO> responseDTOs = userService.getUsers(searchUsersFilter, pageable);

        verify(userRepository).findAll(any(Specification.class), eq(pageable));

        assertThat(userPage.getTotalElements()).isEqualTo(1);
        assertThat(responseDTOs).hasSize(1);
    }

    @Test
    void updateUser_withValidData_returnsUser() {
        when(userMapper.toDTO(user)).thenReturn(updateResponse);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponseDTO responseDTO = userService.updateUser(userId, updateDTO);

        verify(userRepository).findById(userId);
        verify(userMapper).toDTO(user);
        assertThat(responseDTO).isEqualTo(updateResponse);
    }

    @Test
    void createUser_withValidData_returnsUser() {
        when(userMapper.toDTO(user)).thenReturn(response);
        when(userMapper.toEntity(createDTO)).thenReturn(user);
        when(userRepository.findByUsername(createDTO.username())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(createDTO.email())).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);


        UserResponseDTO responseDTO = userService.createUser(createDTO);

        verify(userRepository).save(user);
        verify(userMapper).toDTO(user);
        assertThat(responseDTO).isEqualTo(response);
    }

    @Test
    void deleteUser_withValidData_returnsUser() {
        doNothing()
                .when(userRepository)
                .deleteById(userId);

        userRepository.deleteById(userId);

        verify(userRepository).deleteById(userId);
    }
}
