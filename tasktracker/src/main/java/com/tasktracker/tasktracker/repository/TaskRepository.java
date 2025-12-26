package com.tasktracker.tasktracker.repository;

import com.tasktracker.tasktracker.DTOs.TaskResponse;
import com.tasktracker.tasktracker.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByTaskListId(Long taskListId);
    Task findByIdAndTaskListId(Long taskId, Long taskListId);
}
