package com.tasktracker.tasktracker.mapper;

import com.tasktracker.tasktracker.DTOs.TaskResponse;
import com.tasktracker.tasktracker.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskResponse toResponse(Task task){
        TaskResponse mapper = new TaskResponse();
        mapper.setId(task.getId());
        mapper.setTitle(task.getTitle());
        mapper.setDescription(task.getDescription());
        mapper.setPriority(task.getPriority());
        mapper.setStatus(task.getStatus());
        mapper.setEndDate(task.getEndDate());

        return mapper;
    }

}
