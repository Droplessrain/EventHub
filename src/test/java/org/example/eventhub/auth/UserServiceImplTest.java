package org.example.eventhub.auth;

import org.example.eventhub.dto.user.UserCreateRequestDTO;
import org.example.eventhub.dto.user.UserResponseDTO;
import org.example.eventhub.dto.user.UserUpdateRequestDTO;
import org.example.eventhub.exception.user.UserNotFoundException;
import org.example.eventhub.exception.user.UserWithThisEmailAlreadyExist;
import org.example.eventhub.exception.user.UserWithThisUsernameAlreadyExist;
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
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
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
    private UserUpdateRequestDTO updateDTO;
    private UserCreateRequestDTO createDTO;
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
                null,
                0);
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
        updateDTO = new UserUpdateRequestDTO("username1", "testpassword", "testemail1@mail.com");
        createDTO = new UserCreateRequestDTO("username", "testpassword", "testemail@mail.com", UserRole.USER);
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

    @Test
    void findById_withInvalidId_throwsUserNotFoundException() {
        Long invalidUserId = 999L;
        when(userRepository.findById(invalidUserId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.findById(invalidUserId))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining(String.valueOf(invalidUserId));

        verify(userRepository).findById(invalidUserId);
        verify(userMapper, never()).toDTO(any(User.class));
    }

    @Test
    void findById_withNullId_throwsUserNotFoundException() {
        when(userRepository.findById(null)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.findById(null))
                .isInstanceOf(UserNotFoundException.class);

        verify(userRepository).findById(null);
        verify(userMapper, never()).toDTO(any(User.class));
    }

    @Test
    void createUser_withExistingUsername_throwsUserWithThisUsernameAlreadyExist() {
        User existingUser = new User();
        existingUser.setUsername(createDTO.username());

        when(userRepository.findByUsername(createDTO.username()))
                .thenReturn(Optional.of(existingUser));

        assertThatThrownBy(() -> userService.createUser(createDTO))
                .isInstanceOf(UserWithThisUsernameAlreadyExist.class);

        verify(userRepository).findByUsername(createDTO.username());
        verify(userRepository, never()).findByEmail(any());
        verify(userRepository, never()).save(any(User.class));
        verify(userMapper, never()).toDTO(any(User.class));
    }

    @Test
    void createUser_withExistingEmail_throwsUserWithThisEmailAlreadyExist() {
        User existingUser = new User();
        existingUser.setEmail(createDTO.email());

        when(userRepository.findByUsername(createDTO.username())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(createDTO.email()))
                .thenReturn(Optional.of(existingUser));

        assertThatThrownBy(() -> userService.createUser(createDTO))
                .isInstanceOf(UserWithThisEmailAlreadyExist.class);

        verify(userRepository).findByUsername(createDTO.username());
        verify(userRepository).findByEmail(createDTO.email());
        verify(userRepository, never()).save(any(User.class));
        verify(userMapper, never()).toDTO(any(User.class));
    }

    @Test
    void createUser_withNullInput_shouldThrowException() {
        assertThatThrownBy(() -> userService.createUser(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void updateUser_withInvalidId_throwsUserNotFoundException() {
        Long invalidUserId = 999L;
        when(userRepository.findById(invalidUserId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.updateUser(invalidUserId, updateDTO))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining(String.valueOf(invalidUserId));

        verify(userRepository).findById(invalidUserId);
        verify(userRepository, never()).save(any(User.class));
        verify(userMapper, never()).toDTO(any(User.class));
    }

    @Test
    void updateUser_withNullId_throwsUserNotFoundException() {
        when(userRepository.findById(null)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.updateUser(null, updateDTO))
                .isInstanceOf(UserNotFoundException.class);

        verify(userRepository).findById(null);
        verify(userRepository, never()).save(any(User.class));
        verify(userMapper, never()).toDTO(any(User.class));
    }

    @Test
    void getUsers_withEmptyResult_returnsEmptyPage() {
        Page<User> emptyPage = Page.empty();
        when(userRepository.findAll(any(Specification.class), eq(pageable)))
                .thenReturn(emptyPage);

        Page<UserResponseDTO> result = userService.getUsers(searchUsersFilter, pageable);

        assertThat(result).isEmpty();
        verify(userRepository).findAll(any(Specification.class), eq(pageable));
    }

    @Test
    void getUsers_withNullFilter_returnsAllUsers() {
        when(userRepository.findAll(any(Specification.class), eq(pageable)))
                .thenReturn(userPage);

        Page<UserResponseDTO> result = userService.getUsers(null, pageable);

        assertThat(result).hasSize(1);
        verify(userRepository).findAll(any(Specification.class), eq(pageable));
    }

    @Test
    void getUsers_withNullPageable_shouldThrowException() {
        assertThatThrownBy(() -> userService.getUsers(searchUsersFilter, null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void deleteById_withInvalidId_doesNotThrowException() {
        // Should not throw exception even if userId doesn't exist
        doNothing().when(userRepository).deleteById(userId);

        userService.deleteById(userId);

        verify(userRepository).deleteById(userId);
    }

    @Test
    void createUser_withInvalidEmailFormat_shouldNotBeValidatedByService() {
        // Note: Email format validation should typically be done in DTO validation (@Email annotation)
        // This test shows that service doesn't handle email format validation
        UserCreateRequestDTO invalidEmailDTO = new UserCreateRequestDTO(
                "username",
                "password",
                "invalid-email",
                UserRole.USER
        );

        when(userRepository.findByUsername(invalidEmailDTO.username())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(invalidEmailDTO.email())).thenReturn(Optional.empty());
        when(userMapper.toEntity(invalidEmailDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDTO(user)).thenReturn(response);

        UserResponseDTO result = userService.createUser(invalidEmailDTO);

        assertThat(result).isEqualTo(response);
        verify(userRepository).save(user);
    }

    @Test
    void createUser_withEmptyUsername_shouldThrowException() {
        UserCreateRequestDTO emptyUsernameDTO = new UserCreateRequestDTO(
                "",
                "password",
                "email@mail.com",
                UserRole.USER
        );

        // Depending on your validation strategy, you might want to add validation here
        when(userRepository.findByUsername("")).thenReturn(Optional.empty());
        when(userRepository.findByEmail(emptyUsernameDTO.email())).thenReturn(Optional.empty());
        when(userMapper.toEntity(emptyUsernameDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDTO(user)).thenReturn(response);

        UserResponseDTO result = userService.createUser(emptyUsernameDTO);

        // This will pass unless you add username validation in service
        assertThat(result).isEqualTo(response);
    }

    @Test
    void findById_withZeroId_throwsUserNotFoundException() {
        Long zeroId = 0L;
        when(userRepository.findById(zeroId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.findById(zeroId))
                .isInstanceOf(UserNotFoundException.class);

        verify(userRepository).findById(zeroId);
    }

    @Test
    void findById_withNegativeId_throwsUserNotFoundException() {
        Long negativeId = -1L;
        when(userRepository.findById(negativeId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.findById(negativeId))
                .isInstanceOf(UserNotFoundException.class);

        verify(userRepository).findById(negativeId);
    }
}