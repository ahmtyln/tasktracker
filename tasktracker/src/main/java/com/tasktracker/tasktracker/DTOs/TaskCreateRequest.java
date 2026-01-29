package com.tasktracker.tasktracker.DTOs;

import com.tasktracker.tasktracker.model.TaskList;

import com.tasktracker.tasktracker.model.TaskPriority;
import com.tasktracker.tasktracker.model.TaskStatus;
import com.tasktracker.tasktracker.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TaskCreateRequest {
    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    private TaskPriority priority;
    private TaskStatus status;
    private LocalDate endDate;
}
