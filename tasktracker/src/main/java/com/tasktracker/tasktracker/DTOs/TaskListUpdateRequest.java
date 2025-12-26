package com.tasktracker.tasktracker.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TaskListUpdateRequest {
    private String title;
    private String description;
}
