package com.tasktracker.tasktracker.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotBlank(message = "Email cannot be empty.")
    @Email(message = "Email format is invalid.")
    private String email;

    @NotBlank(message = "Password cannot be empty.")
    @Size(min=2,message = "Password cannot be less then 2 characters.")
    private String password;
}
