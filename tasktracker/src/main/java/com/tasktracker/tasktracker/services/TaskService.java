package com.tasktracker.tasktracker.services;

import com.tasktracker.tasktracker.model.Task;
import com.tasktracker.tasktracker.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository=taskRepository;
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task getTaskById(Long taskId){
        return taskRepository.findById(taskId).orElse(null);
    }

    public Task updateTaskById(Long taskId, Task newTask){
        Task task = taskRepository.findById(taskId).orElse(null);
        if(task==null) return null;
        task.setTitle(newTask.getTitle());
        task.setDescription(newTask.getDescription());
        task.setTaskList(newTask.getTaskList());
        task.setUser(newTask.getUser());

        return taskRepository.save(task);
    }

    public void deleteTaskById(Long taskId){
        taskRepository.deleteById(taskId);
    }

    public List<Task> getAllTaskOfUserByUserId(Long userId){
        List<Task> userTaskList;
        userTaskList = taskRepository.findByUserId(userId);
        return userTaskList;
    }

    public Task updateTaskOfUserByTaskId(Long userId, Long taskId, Task newTask){

        return taskRepository.findByUserId(userId).stream().filter(t -> t.getId().equals(taskId))
                .findFirst()
                .map(task ->{
                    task.setTitle(newTask.getTitle());
                    task.setDescription(newTask.getDescription());
                    task.setUser(newTask.getUser());
                    task.setTaskList(newTask.getTaskList());
                    return taskRepository.save(task);
        })
                .orElse(null);
    }


    public Task getOneTaskOfUserByTaskId(Long userId, Long taskId){
        return taskRepository.findByUserId(userId)
                .stream()
                .filter(t-> t.getId().equals(taskId))
                .findFirst()
                .orElse(null);
    }

    public void deleteTaskOfUserByTaskId(Long userId, Long taskId){
        Task task = taskRepository.findByUserId(userId)
                .stream()
                .filter(t-> t.getId().equals(taskId))
                .findFirst()
                .orElse(null);

        if(task!= null){
            taskRepository.delete(task);
        }

        
    }

}
