package com.tasktracker.tasktracker.services;

import com.tasktracker.tasktracker.DTOs.TaskCreateRequest;
import com.tasktracker.tasktracker.DTOs.TaskListCreateRequest;
import com.tasktracker.tasktracker.DTOs.TaskListResponse;
import com.tasktracker.tasktracker.DTOs.TaskListUpdateRequest;
import com.tasktracker.tasktracker.exceptions.NotFoundException;
import com.tasktracker.tasktracker.mapper.TaskListMapper;
import com.tasktracker.tasktracker.model.TaskList;
import com.tasktracker.tasktracker.repository.TaskListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskListService {
    private final TaskListRepository taskListRepository;
    private final TaskListMapper taskListMapper;

    public TaskListService(TaskListRepository taskListRepository,TaskListMapper taskListMapper){
        this.taskListRepository=taskListRepository;
        this.taskListMapper = taskListMapper;
    }

    public List<TaskListResponse> getAllTaskLists(){
        return taskListRepository.findAll().stream().map(taskListMapper::toResponse).toList();
    }

    public TaskListResponse getOneTaskList(Long taskListId){
        TaskList tasklist = taskListRepository.findById(taskListId).orElse(null);
        if(tasklist == null){
            throw new NotFoundException("TaskList did not found!");
        }
        return taskListMapper.toResponse(tasklist);
    }

    public TaskListResponse createTaskList(TaskListCreateRequest request){
        TaskList taskList = new TaskList();
        taskList.setTitle(request.getTitle());
        taskList.setDescription(request.getDescription());
        TaskList createdTaskList = taskListRepository.save(taskList);

        return taskListMapper.toResponse(createdTaskList);
}

    public TaskListResponse updateTaskList(Long taskListId, TaskListUpdateRequest request){
        TaskList taskList1 = taskListRepository.findById(taskListId).orElse(null);
        if(taskList1 == null){
            throw new NotFoundException("TaskList did not found!");
        }

        taskList1.setTitle(request.getTitle());
        taskList1.setDescription(request.getDescription());

        TaskList updatedTaskList = taskListRepository.save(taskList1);
        return taskListMapper.toResponse(updatedTaskList);
    }

    public void deleteTaskList(Long taskListId){
        taskListRepository.deleteById(taskListId);
    }



}
