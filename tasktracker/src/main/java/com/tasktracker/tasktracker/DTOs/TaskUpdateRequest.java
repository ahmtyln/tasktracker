package com.tasktracker.tasktracker.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TaskUpdateRequest {
    private String title;
    private String description;
    private LocalDate endDate;
}
