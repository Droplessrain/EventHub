package org.example.eventhub.auth;

import org.example.eventhub.dto.security.JwtResponse;
import org.example.eventhub.dto.security.LoginRequest;
import org.example.eventhub.dto.security.RefreshTokenRequest;
import org.example.eventhub.dto.user.UserCreateDTO;
import org.example.eventhub.dto.user.UserResponseDTO;
import org.example.eventhub.exception.InvalidPasswordForUsernameException;
import org.example.eventhub.exception.InvalidRefreshTokenException;
import org.example.eventhub.exception.InvalidUsernameInLoginRequestException;
import org.example.eventhub.mapper.UserMapper;
import org.example.eventhub.model.entity.User;
import org.example.eventhub.model.enums.UserRole;
import org.example.eventhub.repository.UserRepository;
import org.example.eventhub.security.JwtUtils;
import org.example.eventhub.service.UserService;
import org.example.eventhub.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    private LoginRequest validLoginRequest;
    private UserCreateDTO validUserCreateDTO;
    private UserResponseDTO userResponseDTO;
    private RefreshTokenRequest validRefreshTokenRequest;
    private User testUser;
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        validLoginRequest = new LoginRequest("testuser", "password123");
        validUserCreateDTO = new UserCreateDTO(
                "testuser",
                "password123",
                "test@example.com",
                UserRole.USER
        );
        userResponseDTO = new UserResponseDTO(
                1L,
                "testuser",
                "test@example.com",
                UserRole.USER,
                "testemail@email.com"
        );
        validRefreshTokenRequest = new RefreshTokenRequest("valid-refresh-token");

        String encodedPassword = "$2a$10$N9qo8uLOickgx2ZMRZoMye.ML7LfLcFbWtCJ1N8ZkqQ7Yl2oXpQ0O";
        testUser = new User(1L, "testuser", encodedPassword, "test@example.com", UserRole.USER, LocalDateTime.now(), LocalDateTime.now(), 0);

        userDetails = new org.springframework.security.core.userdetails.User(
                "testuser",
                encodedPassword,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }

    @Test
    void login_with_valid_credentials_returns_JwtResponse() {
        when(userRepository.findByUsername("testuser"))
                .thenReturn(Optional.of(testUser));

        String rawPassword = "password123";
        String encodedPassword = testUser.getPassword();

        when(passwordEncoder.matches(eq(rawPassword), eq(encodedPassword)))
                .thenReturn(true);

        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        when(jwtUtils.generateAccessToken(userDetails))
                .thenReturn("access-token-123");
        when(jwtUtils.generateRefreshToken(userDetails))
                .thenReturn("refresh-token-456");
        when(jwtUtils.isRefreshToken(any())).thenReturn(true);

        JwtResponse result = authService.login(validLoginRequest);

        assertThat(result).isNotNull();
        assertThat(result.accessToken()).isEqualTo("access-token-123");
        assertThat(result.refreshToken()).isEqualTo("refresh-token-456");
        assertThat(jwtUtils.isRefreshToken(result.refreshToken())).isTrue(); //check expiration and issuedAt in isRefreshToken

        verify(userRepository).findByUsername("testuser");
        verify(passwordEncoder).matches(eq(rawPassword), eq(encodedPassword));
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtUtils).generateAccessToken(userDetails);
        verify(jwtUtils).generateRefreshToken(userDetails);
    }

    @Test
    void signUp_withValidData_returnsJwtResponse() {
        when(userService.createUser(validUserCreateDTO)).thenReturn(userResponseDTO);
        when(userMapper.toLoginRequest(userResponseDTO))
                .thenReturn(new LoginRequest("testuser", "password123"));

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(testUser));

        when(passwordEncoder.matches(eq("password123"), eq(testUser.getPassword())))
                .thenReturn(true);

        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        when(jwtUtils.generateAccessToken(userDetails)).thenReturn("new-access-token");
        when(jwtUtils.generateRefreshToken(userDetails)).thenReturn("new-refresh-token");

        JwtResponse result = authService.signUp(validUserCreateDTO);

        assertThat(result).isNotNull();
        assertThat(result.accessToken()).isEqualTo("new-access-token");
        assertThat(result.refreshToken()).isEqualTo("new-refresh-token");

        verify(userService).createUser(validUserCreateDTO);
        verify(userMapper).toLoginRequest(userResponseDTO);
        verify(passwordEncoder).matches(eq("password123"), eq(testUser.getPassword()));
    }

    @Test
    void refresh_withValidToken_returnsNewJwtResponse() {
        String refreshToken = "valid-refresh-token";
        RefreshTokenRequest request = new RefreshTokenRequest(refreshToken);

        when(jwtUtils.isRefreshToken(refreshToken)).thenReturn(true);
        when(jwtUtils.generateUserIdFromToken(refreshToken)).thenReturn(1L);

        when(userService.findById(1L)).thenReturn(userResponseDTO);
        when(userMapper.toEntity(userResponseDTO)).thenReturn(testUser);
        
        when(jwtUtils.generateAccessToken(testUser)).thenReturn("new-access-token");

        JwtResponse result = authService.refresh(request);

        assertThat(result).isNotNull();
        assertThat(result.accessToken()).isEqualTo("new-access-token");
        assertThat(result.refreshToken()).isEqualTo(refreshToken);

        verify(jwtUtils).isRefreshToken(refreshToken);
        verify(jwtUtils).generateUserIdFromToken(refreshToken);
        verify(userService).findById(1L);
        verify(userMapper).toEntity(userResponseDTO);
        verify(jwtUtils).generateAccessToken(testUser);
    }

    @Test
    void login_withInvalidUsername_throwsInvalidUsernameInLoginRequestException() {
        when(userRepository.findByUsername("nonexistent"))
                .thenReturn(Optional.empty());

        LoginRequest invalidRequest = new LoginRequest("nonexistent", "password123");

        assertThatThrownBy(() -> authService.login(invalidRequest))
                .isInstanceOf(InvalidUsernameInLoginRequestException.class);

        verify(userRepository).findByUsername("nonexistent");
        verify(passwordEncoder, never()).matches(any(), any());
        verify(authenticationManager, never()).authenticate(any());
        verify(jwtUtils, never()).generateRefreshToken(any());
    }

    @Test
    void login_withInvalidPassword_throwsInvalidPasswordForUsernameException() {
        when(userRepository.findByUsername("testuser"))
                .thenReturn(Optional.of(testUser));

        when(passwordEncoder.matches(eq("wrongpassword"), eq(testUser.getPassword())))
                .thenReturn(false);

        LoginRequest invalidRequest = new LoginRequest("testuser", "wrongpassword");

        assertThatThrownBy(() -> authService.login(invalidRequest))
                .isInstanceOf(InvalidPasswordForUsernameException.class);

        verify(userRepository).findByUsername("testuser");
        verify(passwordEncoder).matches(eq("wrongpassword"), eq(testUser.getPassword()));
        verify(authenticationManager, never()).authenticate(any());
        verify(jwtUtils, never()).generateAccessToken((User) any());
        verify(jwtUtils, never()).generateRefreshToken(any());
    }

    @Test
    void login_withNullUsername_throwsException() {
        LoginRequest nullUsernameRequest = new LoginRequest(null, "password123");

        assertThatThrownBy(() -> authService.login(nullUsernameRequest))
                .isInstanceOf(InvalidUsernameInLoginRequestException.class);

        verify(userRepository).findByUsername(null);
    }

    @Test
    void login_withEmptyUsername_throwsException() {
        when(userRepository.findByUsername(""))
                .thenReturn(Optional.empty());

        LoginRequest emptyUsernameRequest = new LoginRequest("", "password123");

        assertThatThrownBy(() -> authService.login(emptyUsernameRequest))
                .isInstanceOf(InvalidUsernameInLoginRequestException.class);

        verify(userRepository).findByUsername("");
    }

    @Test
    void login_withNullPassword_throwsException() {
        when(userRepository.findByUsername("testuser"))
                .thenReturn(Optional.of(testUser));

        when(passwordEncoder.matches(eq(null), eq(testUser.getPassword())))
                .thenReturn(false);

        LoginRequest nullPasswordRequest = new LoginRequest("testuser", null);

        assertThatThrownBy(() -> authService.login(nullPasswordRequest))
                .isInstanceOf(InvalidPasswordForUsernameException.class);

        verify(userRepository).findByUsername("testuser");
        verify(passwordEncoder).matches(eq(null), eq(testUser.getPassword()));
    }

    @Test
    void login_withEmptyPassword_throwsException() {
        when(userRepository.findByUsername("testuser"))
                .thenReturn(Optional.of(testUser));

        when(passwordEncoder.matches(eq(""), eq(testUser.getPassword())))
                .thenReturn(false);

        LoginRequest emptyPasswordRequest = new LoginRequest("testuser", "");

        assertThatThrownBy(() -> authService.login(emptyPasswordRequest))
                .isInstanceOf(InvalidPasswordForUsernameException.class);

        verify(userRepository).findByUsername("testuser");
        verify(passwordEncoder).matches(eq(""), eq(testUser.getPassword()));
    }

    @Test
    void refresh_withInvalidRefreshToken_throwsInvalidRefreshTokenException() {
        String invalidToken = "invalid-token";
        RefreshTokenRequest request = new RefreshTokenRequest(invalidToken);

        when(jwtUtils.isRefreshToken(invalidToken)).thenReturn(false);

        assertThatThrownBy(() -> authService.refresh(request))
                .isInstanceOf(InvalidRefreshTokenException.class);

        verify(jwtUtils).isRefreshToken(invalidToken);
        verify(jwtUtils, never()).generateUserIdFromToken(any());
        verify(userService, never()).findById(any());
    }

    @Test
    void refresh_withExpiredRefreshToken_throwsInvalidRefreshTokenException() {
        String expiredToken = "expired-refresh-token";
        RefreshTokenRequest request = new RefreshTokenRequest(expiredToken);

        when(jwtUtils.isRefreshToken(expiredToken)).thenReturn(false);

        assertThatThrownBy(() -> authService.refresh(request))
                .isInstanceOf(InvalidRefreshTokenException.class);

        verify(jwtUtils).isRefreshToken(expiredToken);
    }

    @Test
    void refresh_withEmptyToken_throwsInvalidRefreshTokenException() {
        RefreshTokenRequest request = new RefreshTokenRequest("");

        when(jwtUtils.isRefreshToken("")).thenReturn(false);

        assertThatThrownBy(() -> authService.refresh(request))
                .isInstanceOf(InvalidRefreshTokenException.class);

        verify(jwtUtils).isRefreshToken("");
    }

    @Test
    void refresh_whenUserNotFound_throwsException() {
        String validToken = "valid-refresh-token";
        RefreshTokenRequest request = new RefreshTokenRequest(validToken);

        when(jwtUtils.isRefreshToken(validToken)).thenReturn(true);
        when(jwtUtils.generateUserIdFromToken(validToken)).thenReturn(999L);
        when(userService.findById(999L)).thenThrow(new RuntimeException("User not found"));

        assertThatThrownBy(() -> authService.refresh(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("User not found");

        verify(jwtUtils).isRefreshToken(validToken);
        verify(jwtUtils).generateUserIdFromToken(validToken);
        verify(userService).findById(999L);
    }

    @Test
    void signUp_withInvalidData_throwsException() {
        UserCreateDTO invalidCreateDTO = new UserCreateDTO(
                "testuser",
                "short", // слишком короткий пароль
                "invalid-email",
                UserRole.USER
        );

        when(userService.createUser(invalidCreateDTO))
                .thenThrow(new RuntimeException("Validation failed"));

        assertThatThrownBy(() -> authService.signUp(invalidCreateDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Validation failed");

        verify(userService).createUser(invalidCreateDTO);
        verify(userMapper, never()).toLoginRequest(any());
    }

    @Test
    void signUp_withNullData_throwsException() {
        assertThatThrownBy(() -> authService.signUp(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void signUp_thenLoginFails_propagatesException() {
        // Тест на случай, если signUp успешен, но login падает
        when(userService.createUser(validUserCreateDTO)).thenReturn(userResponseDTO);
        when(userMapper.toLoginRequest(userResponseDTO))
                .thenReturn(new LoginRequest("testuser", "password123"));

        // Эмулируем ситуацию, когда пользователь создан, но логин не удается
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authService.signUp(validUserCreateDTO))
                .isInstanceOf(InvalidUsernameInLoginRequestException.class);

        verify(userService).createUser(validUserCreateDTO);
        verify(userMapper).toLoginRequest(userResponseDTO);
        verify(userRepository).findByUsername("testuser");
    }

    @Test
    void login_withNullRequest_throwsException() {
        assertThatThrownBy(() -> authService.login(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void refresh_withNullRequest_throwsException() {
        assertThatThrownBy(() -> authService.refresh(null))
                .isInstanceOf(NullPointerException.class);
    }
}