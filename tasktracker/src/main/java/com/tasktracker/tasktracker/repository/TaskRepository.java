package com.tasktracker.tasktracker.repository;

import com.tasktracker.tasktracker.DTOs.TaskResponse;
import com.tasktracker.tasktracker.model.Task;
import com.tasktracker.tasktracker.model.TaskList;
import com.tasktracker.tasktracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByTaskListAndUser(TaskList taskList, User user);
    Optional<Task> findByIdAndTaskListAndUser(Long taskId, TaskList taskList, User user);

    Long countByTaskList(TaskList taskList);

}
