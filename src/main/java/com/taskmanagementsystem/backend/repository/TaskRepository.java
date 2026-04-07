package com.taskmanagementsystem.backend.repository;

import com.taskmanagementsystem.backend.entity.Task;
import com.taskmanagementsystem.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.taskmanagementsystem.backend.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // Get all tasks of a specific user
    Page<Task> findByUser(User user, Pageable pageable);

    Page<Task> findByUserAndStatus(User user, TaskStatus status, Pageable pageable);
}