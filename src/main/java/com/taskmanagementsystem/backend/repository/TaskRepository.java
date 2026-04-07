package com.taskmanagementsystem.backend.repository;

import com.taskmanagementsystem.backend.entity.Task;
import com.taskmanagementsystem.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // Get all tasks of a specific user
    List<Task> findByUser(User user);
}