package com.tasktracker.tasktracker.services;

import com.tasktracker.tasktracker.DTOs.AuthResponse;
import com.tasktracker.tasktracker.DTOs.UserCreateRequest;
import com.tasktracker.tasktracker.config.SecurityConfig;
import com.tasktracker.tasktracker.model.User;
import com.tasktracker.tasktracker.repository.UserRepository;
import com.tasktracker.tasktracker.roles.Roles;
import com.tasktracker.tasktracker.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {
    
    private final CustomUserDetailsService customUserDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(UserCreateRequest request){
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("User already exist.");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(
                request.getUsername(),
                request.getEmail(),
                encodedPassword
        );

        User savedUser = userRepository.save(user);

        CustomUserDetails userDetails = new CustomUserDetails(savedUser);




    }
}
