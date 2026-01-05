package com.tasktracker.tasktracker.controllers;

import com.tasktracker.tasktracker.DTOs.UserCreateRequest;
import com.tasktracker.tasktracker.DTOs.UserResponse;
import com.tasktracker.tasktracker.DTOs.UserUpdateRequest;
import com.tasktracker.tasktracker.model.User;
import com.tasktracker.tasktracker.services.UserService;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUserById(@PathVariable("id") Long userId, @Valid @RequestBody UserUpdateRequest request){
        return ResponseEntity.ok(userService.updateUserById(userId,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long userId){
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }


}
