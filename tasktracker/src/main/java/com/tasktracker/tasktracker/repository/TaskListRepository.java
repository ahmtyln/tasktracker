package com.tasktracker.tasktracker.repository;

import com.tasktracker.tasktracker.model.Task;
import com.tasktracker.tasktracker.model.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {
}
