package com.tasktracker.tasktracker.repository;

import com.tasktracker.tasktracker.DTOs.TaskListResponse;
import com.tasktracker.tasktracker.model.Task;
import com.tasktracker.tasktracker.model.TaskList;
import com.tasktracker.tasktracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {
    List<TaskList> findByUser(User user);
    Optional<TaskList> findByIdAndUser(Long taskListId, User user);
}
