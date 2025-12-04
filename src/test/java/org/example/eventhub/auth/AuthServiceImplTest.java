package org.example.eventhub.auth;

import org.example.eventhub.dto.security.JwtResponse;
import org.example.eventhub.dto.security.LoginRequest;
import org.example.eventhub.dto.security.RefreshTokenRequest;
import org.example.eventhub.dto.user.UserCreateDTO;
import org.example.eventhub.dto.user.UserResponseDTO;
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
        testUser = new User(1L, "testuser", encodedPassword, "test@example.com", UserRole.USER, LocalDateTime.now(), LocalDateTime.now());

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
}