package com.tasktracker.tasktracker.services;

import com.tasktracker.tasktracker.model.User;
import com.tasktracker.tasktracker.repository.UserRepository;
import com.tasktracker.tasktracker.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticatedUserService {
    private final UserRepository userRepository;


    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null || !(authentication.getPrincipal() instanceof CustomUserDetails userDetails)){
            throw new RuntimeException("Unauthenticated access");
        }

        return userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
