package com.tasktracker.tasktracker.controllers;

import com.tasktracker.tasktracker.DTOs.AuthResponse;
import com.tasktracker.tasktracker.DTOs.UserCreateRequest;
import com.tasktracker.tasktracker.security.CustomUserDetails;
import com.tasktracker.tasktracker.services.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterService registerService;

    @PostMapping
    public AuthResponse register(@RequestBody UserCreateRequest request){
        return ResponseEntity.ok(registerService.register(request)) ;
    }
}
