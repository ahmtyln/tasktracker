package com.tasktracker.tasktracker.services;

import com.tasktracker.tasktracker.model.User;
import com.tasktracker.tasktracker.repository.UserRepository;
import com.tasktracker.tasktracker.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public CustomUserDetails loadUserByUsername (String username) throws UsernameNotFoundException{
        User foundUser = userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("There is no User!"));
        return new CustomUserDetails(foundUser);

    }

}
