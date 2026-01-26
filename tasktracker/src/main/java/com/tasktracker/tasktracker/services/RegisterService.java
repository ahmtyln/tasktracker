package com.tasktracker.tasktracker.services;

import com.tasktracker.tasktracker.DTOs.AuthResponse;
import com.tasktracker.tasktracker.DTOs.UserCreateRequest;
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
    private final JwtService jwtService;

    public AuthResponse register(UserCreateRequest request){
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("User already exist.");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = User
                .builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(encodedPassword)
                .role(Roles.ROLE_USER)
                .build();

        User savedUser = userRepository.save(user);

        CustomUserDetails userDetails = new CustomUserDetails(savedUser);

        String token = jwtService.generateToken(userDetails);

        return AuthResponse
                .builder()
                .token(token)
                .username(userDetails.getUsername())
                .email(userDetails.getUsername())
                .role(userDetails.getAuthorities().iterator().next().getAuthority())
                .build();

    }
}
