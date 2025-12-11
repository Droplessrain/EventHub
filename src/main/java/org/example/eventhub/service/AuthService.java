package org.example.eventhub.service;

import org.example.eventhub.dto.security.JwtResponse;
import org.example.eventhub.dto.security.LoginRequest;
import org.example.eventhub.dto.security.RefreshTokenRequest;
import org.example.eventhub.dto.user.UserCreateRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    JwtResponse login(LoginRequest request);
    JwtResponse refresh(RefreshTokenRequest refreshTokenRequest);
    JwtResponse signUp(UserCreateRequestDTO userCreateDTO);
}
