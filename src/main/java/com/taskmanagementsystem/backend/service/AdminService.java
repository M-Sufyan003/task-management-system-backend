package com.taskmanagementsystem.backend.service;

import com.taskmanagementsystem.backend.dto.TaskDTO;
import com.taskmanagementsystem.backend.dto.UserDTO;
import com.taskmanagementsystem.backend.entity.TaskStatus;

import java.util.List;

public interface AdminService {

    // Users
    List<UserDTO> getAllUsers();
    void deleteUser(Long userId);

    // Tasks
    List<TaskDTO> getAllTasks(TaskStatus status); // for admin
    TaskDTO updateTask(Long taskId, TaskDTO taskDTO);
    void deleteTask(Long taskId);
}