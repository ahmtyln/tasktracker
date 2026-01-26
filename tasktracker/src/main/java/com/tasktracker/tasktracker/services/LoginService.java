package com.tasktracker.tasktracker.services;

import com.tasktracker.tasktracker.DTOs.AuthResponse;
import com.tasktracker.tasktracker.DTOs.LoginDto;
import com.tasktracker.tasktracker.DTOs.UserCreateRequest;
import com.tasktracker.tasktracker.repository.UserRepository;
import com.tasktracker.tasktracker.security.CustomUserDetails;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Builder
public class LoginService {
    private final UserRepository userRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse login(LoginDto request){
       try {
           CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getEmail());
           if(passwordEncoder.matches(request.getPassword(), userDetails.getPassword())){
               String token = jwtService.generateToken(userDetails);

               return AuthResponse
                       .builder()
                       .token(token)
                       .username(userDetails.getUsername())
                       .email(userDetails.getUsername())
                       .role(userDetails.getAuthorities().iterator().next().getAuthority())
                       .build();

           }else{
               throw new BadCredentialsException("False Password");
           }
       } catch (UsernameNotFoundException e) {
           throw new UsernameNotFoundException("User ist not found." + request.getEmail());
       } catch (Exception e) {
           throw new RuntimeException("Login error: " + e.getMessage());
       }
    }
}
