package org.example.eventhub.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.eventhub.controller.AuthController;
import org.example.eventhub.dto.security.JwtResponse;
import org.example.eventhub.dto.security.LoginRequest;
import org.example.eventhub.dto.security.RefreshTokenRequest;
import org.example.eventhub.dto.user.UserCreateDTO;
import org.example.eventhub.model.enums.UserRole;
import org.example.eventhub.security.JwtUtils;
import org.example.eventhub.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockitoBean
    JwtUtils jwtUtils;

    @MockitoBean
    AuthService authService;

    LoginRequest loginRequest;
    JwtResponse jwtResponse;
    UserCreateDTO userCreateDTO;
    RefreshTokenRequest refreshTokenRequest;

    @BeforeEach
    void setUp() {
        loginRequest = new LoginRequest("testuser", "testpassword");
        jwtResponse = new JwtResponse("accessToken", "refreshToken");
        userCreateDTO = new UserCreateDTO("testuser", "testpassword", "testemail@mail.com", UserRole.USER);
        refreshTokenRequest = new RefreshTokenRequest("refreshToken");
    }

    @Test
    public void login_With_Valid_Login_Request_isOk() throws Exception {
        when(authService.login(loginRequest)).thenReturn(jwtResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(mapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value("accessToken"))
                .andExpect(jsonPath("$.refreshToken").value("refreshToken"));
        
    }

    @Test
    public void singUp_With_Valid_UserCreateDTO_isCreated() throws Exception {
        when(authService.signUp(userCreateDTO)).thenReturn(jwtResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signup")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(mapper.writeValueAsString(userCreateDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accessToken").value("accessToken"))
                .andExpect(jsonPath("$.refreshToken").value("refreshToken"));
    }

    @Test
    public void refresh_With_Valid_RefreshTocken_return_accessTocken() throws Exception {
        when(authService.refresh(refreshTokenRequest)).thenReturn(jwtResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/refresh")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(mapper.writeValueAsString(refreshTokenRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value("accessToken"))
                .andExpect(jsonPath("$.refreshToken").value("refreshToken"));
    }

}