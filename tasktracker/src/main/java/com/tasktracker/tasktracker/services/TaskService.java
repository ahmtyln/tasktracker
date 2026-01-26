package com.tasktracker.tasktracker.services;

import com.tasktracker.tasktracker.DTOs.TaskCreateRequest;
import com.tasktracker.tasktracker.DTOs.TaskResponse;
import com.tasktracker.tasktracker.DTOs.TaskUpdateRequest;
import com.tasktracker.tasktracker.mapper.TaskMapper;
import com.tasktracker.tasktracker.model.Task;
import com.tasktracker.tasktracker.model.TaskList;
import com.tasktracker.tasktracker.model.User;
import com.tasktracker.tasktracker.repository.TaskListRepository;
import com.tasktracker.tasktracker.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;
    private final AuthenticatedUserService authenticatedUserService;
    private final TaskMapper taskMapper;



    public List<TaskResponse> getAllTasks(Long taskListId){
        User user = authenticatedUserService.getCurrentUser();
        TaskList taskList = taskListRepository.findByIdAndUser(taskListId,user)
                .orElseThrow(() -> new RuntimeException("Tasklist not found"));
        List<Task> tasks = taskRepository.findByTaskListAndUser(taskList,user);
        return tasks
                .stream()
                .map(taskMapper::toResponse)
                .toList();
    }

    public TaskResponse getOneTaskById(Long taskListId, Long taskId){
        User user = authenticatedUserService.getCurrentUser();
        TaskList taskList = taskListRepository.findByIdAndUser(taskListId,user)
                .orElseThrow(() -> new RuntimeException("Tasklist not found"));

        Task task = taskRepository.findByIdAndTaskListAndUser(taskId,taskList,user)
                .orElseThrow(() -> new RuntimeException("Task not found"));;

        return taskMapper.toResponse(task);
    }

    public TaskResponse createTask(Long taskListId, TaskCreateRequest request){
        User user = authenticatedUserService.getCurrentUser();
        TaskList taskList = taskListRepository.findByIdAndUser(taskListId,user)
                .orElseThrow(() -> new RuntimeException("Tasklist not found"));
        Task newTask = new Task();
        newTask.setTitle(request.getTitle());
        newTask.setDescription(request.getDescription());
        newTask.setEndDate(request.getEndDate());
        newTask.setTaskList(taskList);
        newTask.setUser(user);

        Task savedTask =  taskRepository.save(newTask);

        return taskMapper.toResponse(savedTask);
    }

    public TaskResponse updateTaskById(Long taskListId, Long taskId, TaskUpdateRequest request){
        User user = authenticatedUserService.getCurrentUser();
        TaskList taskList = taskListRepository.findByIdAndUser(taskListId,user)
                .orElseThrow(() -> new RuntimeException("Tasklist not found"));

        Task task = taskRepository.findByIdAndTaskListAndUser(taskId,taskList,user)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setEndDate(request.getEndDate());

        Task updatedTask = taskRepository.save(task);
        return taskMapper.toResponse(updatedTask);
    }

    public void deleteTaskById(Long taskListId,Long taskId){
        User user = authenticatedUserService.getCurrentUser();
        TaskList taskList = taskListRepository.findByIdAndUser(taskListId,user)
                .orElseThrow(() -> new RuntimeException("Tasklist not found"));

        Task task = taskRepository.findByIdAndTaskListAndUser(taskId,taskList,user)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        taskRepository.delete(task);
    }

}
