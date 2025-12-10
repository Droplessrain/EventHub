package org.example.eventhub.service.impl;

import lombok.AllArgsConstructor;
import org.example.eventhub.dto.security.JwtResponse;
import org.example.eventhub.dto.security.LoginRequest;
import org.example.eventhub.dto.security.RefreshTokenRequest;
import org.example.eventhub.dto.user.UserCreateDTO;
import org.example.eventhub.dto.user.UserResponseDTO;
import org.example.eventhub.exception.user.InvalidPasswordForUsernameException;
import org.example.eventhub.exception.security.InvalidRefreshTokenException;
import org.example.eventhub.exception.security.InvalidUsernameInLoginRequestException;
import org.example.eventhub.mapper.UserMapper;
import org.example.eventhub.model.entity.User;
import org.example.eventhub.repository.UserRepository;
import org.example.eventhub.security.JwtUtils;
import org.example.eventhub.service.AuthService;
import org.example.eventhub.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.example.eventhub.exception.ErrorConstants.INVALID_PASSWORD_FOR_USERNAME_IN_LOGIN_TOKEN;
import static org.example.eventhub.exception.ErrorConstants.INVALID_REFRESH_TOKEN;
import static org.example.eventhub.exception.ErrorConstants.INVALID_USERNAME_IN_LOGIN_TOKEN;



@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public JwtResponse login(LoginRequest request) {

        Optional<User> user = userRepository.findByUsername(request.username());
        if (user.isPresent()) {
            if(passwordEncoder.matches(request.password(), user.get().getPassword())) {

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
            else{
                throw new InvalidPasswordForUsernameException(INVALID_PASSWORD_FOR_USERNAME_IN_LOGIN_TOKEN);
            }
        }
        else{
            throw new InvalidUsernameInLoginRequestException(INVALID_USERNAME_IN_LOGIN_TOKEN);
        }

    }

    @Override
    public JwtResponse signUp(UserCreateDTO userCreateDTO) {
        UserResponseDTO userResponseDTO = userService.createUser(userCreateDTO);
        return login(userMapper.toLoginRequest(userResponseDTO));
    }

    @Override
    public JwtResponse refresh(RefreshTokenRequest refreshTokenRequest){
        String token = refreshTokenRequest.refreshToken();
        if(jwtUtils.isRefreshToken(token)){
            User user = userMapper.toEntity(userService.findById(jwtUtils.generateUserIdFromToken(token)));
            String accessToken = jwtUtils.generateAccessToken(user);
            return new JwtResponse(accessToken, refreshTokenRequest.refreshToken());
        }
        else throw new InvalidRefreshTokenException(INVALID_REFRESH_TOKEN);
    }
}
