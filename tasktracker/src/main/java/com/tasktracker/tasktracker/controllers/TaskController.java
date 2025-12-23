package com.tasktracker.tasktracker.controllers;

import com.tasktracker.tasktracker.model.Task;
import com.tasktracker.tasktracker.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    TaskService taskService;
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Long taskId){
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTaskById(@PathVariable("id") Long taskId, @RequestBody Task newTask){
        return ResponseEntity.ok(taskService.updateTaskById(taskId,newTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable("id") Long taskId){
        taskService.deleteTaskById(taskId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{userId}/tasks")
    public ResponseEntity<List<Task>> getAllTaskOfUserByUserId(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(taskService.getAllTaskOfUserByUserId(userId));
    }

    @GetMapping("/users/{userId}/tasks/{taskId}")
    public ResponseEntity<Task> getOneTaskOfUserByTaskId(@PathVariable("userId") Long userId,
                                                         @PathVariable("taskId") Long taskId){
        return ResponseEntity.ok(taskService.getOneTaskOfUserByTaskId(userId,taskId));
    }

    @PutMapping("/users/{userId}/tasks/{taskId}")
    public ResponseEntity<Task> updateTaskOfUserByTaskId(@PathVariable("userId") Long userId,
                                                         @PathVariable("taskId") Long taskId,
                                                         @RequestBody Task newTask){
        return ResponseEntity.ok(taskService.updateTaskOfUserByTaskId(userId,taskId,newTask));
    }

    @DeleteMapping("/users/{userId}/tasks/{taskId}")
    public ResponseEntity<Void> deleteTaskOfUserByTaskId(@PathVariable("userId") Long userId,
                                                         @PathVariable("taskId") Long taskId){
        taskService.deleteTaskOfUserByTaskId(userId,taskId);
        return ResponseEntity.noContent().build();
    }


}
