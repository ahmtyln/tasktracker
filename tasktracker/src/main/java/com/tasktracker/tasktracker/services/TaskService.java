package com.tasktracker.tasktracker.services;

import com.tasktracker.tasktracker.DTOs.TaskCreateRequest;
import com.tasktracker.tasktracker.DTOs.TaskResponse;
import com.tasktracker.tasktracker.DTOs.TaskUpdateRequest;
import com.tasktracker.tasktracker.exceptions.NotFoundException;
import com.tasktracker.tasktracker.mapper.TaskMapper;
import com.tasktracker.tasktracker.model.Task;
import com.tasktracker.tasktracker.model.TaskList;
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
    private final TaskMapper taskMapper;


    public List<TaskResponse> getAllTasks(Long taskListId){
        List<Task> tasks = taskRepository.findByTaskListId(taskListId);
        return tasks.stream().map(taskMapper::toResponse).toList();
    }

    public TaskResponse getOneTaskById(Long taskListId, Long taskId){
        Task task = taskRepository.findByIdAndTaskListId(taskId,taskListId);
        if (task == null) {
            throw new NotFoundException("Task not found");
        }
        return taskMapper.toResponse(task);
    }

    public TaskResponse createTask(Long taskListId, TaskCreateRequest request){
        TaskList taskList = taskListRepository.findById(taskListId).orElseThrow(()-> new NotFoundException("TaskList not Found."));
        Task newTask = new Task();
        newTask.setTitle(request.getTitle());
        newTask.setDescription(request.getDescription());
        newTask.setEndDate(request.getEndDate());

        newTask.setTaskList(taskList);

        Task savedTask =  taskRepository.save(newTask);

        return taskMapper.toResponse(savedTask);
    }

    public TaskResponse updateTaskById(Long taskListId, Long taskId, TaskUpdateRequest request){
        Task task = taskRepository.findByIdAndTaskListId(taskId,taskListId);
        if (task == null) {
            throw new NotFoundException("Task not found");
        }
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setEndDate(request.getEndDate());

        Task updatedTask = taskRepository.save(task);
        return taskMapper.toResponse(updatedTask);
    }

    public void deleteTaskById(Long taskListId,Long taskId){
        Task task = taskRepository.findByIdAndTaskListId(taskId,taskListId);
        if (task == null) {
            throw new NotFoundException("Task not found");
        }
        taskRepository.delete(task);
    }

}
