package com.tasktracker.tasktracker.mapper;

import com.tasktracker.tasktracker.DTOs.TaskListResponse;
import com.tasktracker.tasktracker.model.TaskList;
import com.tasktracker.tasktracker.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskListMapper {
    private final TaskRepository taskRepository;

    public TaskListResponse toResponse(TaskList taskList){
        TaskListResponse taskListResponse = new TaskListResponse();
        taskListResponse.setId(taskList.getId());
        taskListResponse.setTitle(taskList.getTitle());
        taskListResponse.setDescription(taskList.getDescription());
        taskListResponse.setTaskCount(taskRepository.countByTaskList(taskList));

        return taskListResponse;
    }
}
