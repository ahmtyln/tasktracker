package com.tasktracker.tasktracker.repository;

import com.tasktracker.tasktracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
