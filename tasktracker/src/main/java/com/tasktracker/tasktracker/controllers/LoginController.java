package com.tasktracker.tasktracker.controllers;

import com.tasktracker.tasktracker.DTOs.AuthResponse;
import com.tasktracker.tasktracker.DTOs.LoginDto;
import com.tasktracker.tasktracker.DTOs.UserCreateRequest;
import com.tasktracker.tasktracker.services.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginDto request){
        return ResponseEntity.ok(loginService.login(request));
    }
}
