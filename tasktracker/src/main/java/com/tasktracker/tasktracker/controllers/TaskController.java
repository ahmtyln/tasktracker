package com.tasktracker.tasktracker.controllers;

import com.tasktracker.tasktracker.DTOs.TaskCreateRequest;
import com.tasktracker.tasktracker.DTOs.TaskResponse;
import com.tasktracker.tasktracker.DTOs.TaskUpdateRequest;
import com.tasktracker.tasktracker.model.Task;
import com.tasktracker.tasktracker.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasklists/{taskListId}/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks(@PathVariable ("taskListId") Long taskListId){
        return ResponseEntity.ok(taskService.getAllTasks(taskListId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getOneTaskById(@PathVariable ("taskListId") Long taskListId, @PathVariable("id") Long taskId){
        return ResponseEntity.ok(taskService.getOneTaskById(taskListId,taskId));
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@PathVariable ("taskListId") Long taskListId,@Valid @RequestBody TaskCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(taskListId,request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTaskById(@PathVariable ("taskListId") Long taskListId,@PathVariable("id") Long taskId, @Valid @RequestBody TaskUpdateRequest request){
        return ResponseEntity.ok(taskService.updateTaskById(taskListId,taskId,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable ("taskListId") Long taskListId,@PathVariable("id") Long taskId){
        taskService.deleteTaskById(taskListId,taskId);
        return ResponseEntity.noContent().build();
    }


}
