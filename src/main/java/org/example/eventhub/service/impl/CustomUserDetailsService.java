package org.example.eventhub.service.impl;

import lombok.AllArgsConstructor;
import org.example.eventhub.model.entity.AuthUserDeteils;
import org.example.eventhub.model.entity.User;
import org.example.eventhub.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final String USER_NOT_FOUND_MESSAGE = "User with name %s not found";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, username)));

        return new AuthUserDeteils(user);
    }
}
