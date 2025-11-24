package org.example.eventhub.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.eventhub.dto.security.JwtResponse;
import org.example.eventhub.dto.security.LoginRequest;
import org.example.eventhub.dto.security.RefreshTokenRequest;
import org.example.eventhub.dto.user.UserCreateDTO;
import org.example.eventhub.dto.user.UserResponseDTO;
import org.example.eventhub.mapper.UserMapper;
import org.example.eventhub.model.entity.User;
import org.example.eventhub.repository.UserRepository;
import org.example.eventhub.security.JwtAuthFilter;
import org.example.eventhub.security.JwtUtils;
import org.example.eventhub.service.AuthService;
import org.example.eventhub.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public JwtResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String accessToken = jwtUtils.generateAccessToken(userDetails);
        String refreshToken = jwtUtils.generateRefreshToken(userDetails);

        return new JwtResponse(accessToken, refreshToken);
    }

    @Override
    public JwtResponse signUp(UserCreateDTO userCreateDTO) {
        UserResponseDTO userResponseDTO = userService.createUser(userCreateDTO);
        return login(userMapper.toLoginRequest(userResponseDTO));
    }

    @Override
    public JwtResponse refresh(RefreshTokenRequest refreshTokenRequest){
        String tocken = refreshTokenRequest.toString();
        if(jwtUtils.isRefreshToken(tocken)){
            User user = userMapper.toEntity(userService.findById(jwtUtils.generateUserIdFromToken(tocken)));
            String accessTocken = jwtUtils.generateAccessToken(user);
            return new JwtResponse(accessTocken, refreshTokenRequest.toString());
        }

        return null;
    }
}
