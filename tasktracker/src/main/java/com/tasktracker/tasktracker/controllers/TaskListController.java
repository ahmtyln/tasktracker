package com.tasktracker.tasktracker.controllers;

import com.tasktracker.tasktracker.DTOs.TaskCreateRequest;
import com.tasktracker.tasktracker.DTOs.TaskListCreateRequest;
import com.tasktracker.tasktracker.DTOs.TaskListResponse;
import com.tasktracker.tasktracker.DTOs.TaskListUpdateRequest;
import com.tasktracker.tasktracker.mapper.TaskListMapper;
import com.tasktracker.tasktracker.model.Task;
import com.tasktracker.tasktracker.model.TaskList;
import com.tasktracker.tasktracker.services.TaskListService;
import com.tasktracker.tasktracker.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasklists")
public class TaskListController {
    private final TaskListService taskListService;


    public TaskListController(TaskListService taskListService){
        this.taskListService = taskListService;
    }

    @GetMapping
    public ResponseEntity<List<TaskListResponse>> getAllTaskLists(){
        return ResponseEntity.ok(taskListService.getAllTaskLists());
    }

    @GetMapping("/{taskListId}")
    public ResponseEntity<TaskListResponse> getOneTaskList(@PathVariable("taskListId") Long taskListId){
        return ResponseEntity.ok(taskListService.getOneTaskList(taskListId));
    }

    @PostMapping
    public ResponseEntity<TaskListResponse> createTaskList(@Valid @RequestBody TaskListCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskListService.createTaskList(request));
    }

    @PutMapping("/{taskListId}")
    public ResponseEntity<TaskListResponse> updateTaskList(@PathVariable("taskListId") Long taskListId,@Valid @RequestBody TaskListUpdateRequest request){
        return ResponseEntity.ok(taskListService.updateTaskList(taskListId,request));
    }

    @DeleteMapping("/{taskListId}")
    public ResponseEntity<Void> deleteTaskList(@PathVariable("taskListId") Long taskListId){
        taskListService.deleteTaskList(taskListId);
        return ResponseEntity.noContent().build();
    }



}
