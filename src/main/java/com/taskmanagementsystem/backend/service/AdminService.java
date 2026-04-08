package com.taskmanagementsystem.backend.service;

import com.taskmanagementsystem.backend.dto.AdminStatsDTO;
import com.taskmanagementsystem.backend.dto.TaskDTO;
import com.taskmanagementsystem.backend.dto.UserDTO;
import com.taskmanagementsystem.backend.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface AdminService {

    // Users
    List<UserDTO> getAllUsers();
    void deleteUser(Long userId);

    // Tasks
    Page<TaskDTO> getAllTasks(TaskStatus status, Pageable pageable); // for admin
    TaskDTO updateTask(Long taskId, TaskDTO taskDTO);
    void deleteTask(Long taskId);

    AdminStatsDTO getSystemStats();
}