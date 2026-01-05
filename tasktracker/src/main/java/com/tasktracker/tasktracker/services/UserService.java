package com.tasktracker.tasktracker.services;

import com.tasktracker.tasktracker.DTOs.UserCreateRequest;
import com.tasktracker.tasktracker.DTOs.UserResponse;
import com.tasktracker.tasktracker.DTOs.UserUpdateRequest;
import com.tasktracker.tasktracker.exceptions.NotFoundException;
import com.tasktracker.tasktracker.mapper.UserMapper;
import com.tasktracker.tasktracker.model.User;
import com.tasktracker.tasktracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public List<UserResponse> getAllUsers(){
        return userRepository.findAll().stream().map(userMapper::toResponse).toList();
    }

    public UserResponse getUserById(Long userId){
        User user = userRepository.findById(userId).orElse(null);
        if(user==null){
            throw new NotFoundException("User did not found.");
        }
        return userMapper.toResponse(user);
    }

    public UserResponse createUser(UserCreateRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        User createdUser = userRepository.save(user);
        return userMapper.toResponse(createdUser);
    }

    public UserResponse updateUserById(Long userId, UserUpdateRequest request){
        User user = userRepository.findById(userId).orElse(null);
        if(user==null){
            throw new NotFoundException("User did not found!");
        }
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        User updatedUser = userRepository.save(user);
        return userMapper.toResponse(updatedUser);
    }


    public void deleteUserById(Long userId){
        userRepository.deleteById(userId);
    }
}
