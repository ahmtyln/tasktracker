package com.tasktracker.tasktracker.services;

import com.tasktracker.tasktracker.model.User;
import com.tasktracker.tasktracker.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElse(null);
    }

    public User saveUser(User newUser){
        return userRepository.save(newUser);
    }

    public User updateUserById(Long userId, User updatedUser){
        User user = userRepository.findById(userId).orElse(null);
        if(user==null) return null;
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setTasks(updatedUser.getTasks());
        user.setTaskLists(updatedUser.getTaskLists());

        return userRepository.save(user);
    }


    public void deleteUserById(Long userId){
        userRepository.deleteById(userId);
    }
}
