package com.tasktracker.tasktracker.services;

import com.tasktracker.tasktracker.DTOs.TaskListCreateRequest;
import com.tasktracker.tasktracker.DTOs.TaskListResponse;
import com.tasktracker.tasktracker.DTOs.TaskListUpdateRequest;
import com.tasktracker.tasktracker.exceptions.NotFoundException;
import com.tasktracker.tasktracker.mapper.TaskListMapper;
import com.tasktracker.tasktracker.model.TaskList;
import com.tasktracker.tasktracker.model.User;
import com.tasktracker.tasktracker.repository.TaskListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskListService {
    private final TaskListRepository taskListRepository;
    private final TaskListMapper taskListMapper;
    private final AuthenticatedUserService authenticatedUserService;



    public List<TaskListResponse> getAllTaskLists(){
        User user = authenticatedUserService.getCurrentUser();
        return taskListRepository
                .findByUser(user)
                .stream()
                .map(taskListMapper::toResponse)
                .toList();
    }

    public TaskListResponse getOneTaskList(Long taskListId){
        User user = authenticatedUserService.getCurrentUser();
        TaskList tasklist = taskListRepository.findByIdAndUser(taskListId, user)
                .orElseThrow(() -> new NotFoundException("TaskList not found"));;

        return taskListMapper.toResponse(tasklist);
    }

    public TaskListResponse createTaskList(TaskListCreateRequest request){
        User user = authenticatedUserService.getCurrentUser();
        TaskList taskList = new TaskList();
        taskList.setTitle(request.getTitle());
        taskList.setDescription(request.getDescription());
        taskList.setUser(user);
        TaskList createdTaskList = taskListRepository.save(taskList);

        return taskListMapper.toResponse(createdTaskList);
}

    public TaskListResponse updateTaskList(Long taskListId, TaskListUpdateRequest request){
        User user = authenticatedUserService.getCurrentUser();
        TaskList taskList1 = taskListRepository
                .findByIdAndUser(taskListId,user)
                .orElseThrow(() -> new NotFoundException("TaskList not found"));;


        taskList1.setTitle(request.getTitle());
        taskList1.setDescription(request.getDescription());

        TaskList updatedTaskList = taskListRepository.save(taskList1);
        return taskListMapper.toResponse(updatedTaskList);
    }

    public void deleteTaskList(Long taskListId){
        User user = authenticatedUserService.getCurrentUser();
        TaskList taskList = taskListRepository.findByIdAndUser(taskListId,user)
                .orElseThrow(() -> new NotFoundException("TaskList not found"));;

        taskListRepository.delete(taskList);
    }



}
