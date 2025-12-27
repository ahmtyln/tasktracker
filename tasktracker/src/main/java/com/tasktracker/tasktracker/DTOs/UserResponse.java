package com.tasktracker.tasktracker.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    private Long id;

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3,max = 100)
    private String username;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email format is invalid.")
    private String email;
}
