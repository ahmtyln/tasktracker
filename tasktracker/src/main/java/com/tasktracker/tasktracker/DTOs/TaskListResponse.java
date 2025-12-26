package com.tasktracker.tasktracker.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TaskListResponse {
    private Long id;
    private String title;
    private String description;
}
