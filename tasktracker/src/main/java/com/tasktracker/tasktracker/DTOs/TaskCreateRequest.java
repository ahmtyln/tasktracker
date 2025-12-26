package com.tasktracker.tasktracker.DTOs;

import com.tasktracker.tasktracker.model.TaskList;

import com.tasktracker.tasktracker.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TaskCreateRequest {
    private String title;
    private String description;
    private LocalDate endDate;
}
