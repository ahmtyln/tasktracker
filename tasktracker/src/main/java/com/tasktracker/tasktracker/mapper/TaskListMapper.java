package com.tasktracker.tasktracker.mapper;

import com.tasktracker.tasktracker.DTOs.TaskListResponse;
import com.tasktracker.tasktracker.model.TaskList;
import org.springframework.stereotype.Component;

@Component
public class TaskListMapper {
    public TaskListResponse toResponse(TaskList taskList){
        TaskListResponse taskListResponse = new TaskListResponse();
        taskListResponse.setId(taskList.getId());
        taskListResponse.setTitle(taskList.getTitle());
        taskListResponse.setDescription(taskList.getDescription());

        return taskListResponse;
    }
}
