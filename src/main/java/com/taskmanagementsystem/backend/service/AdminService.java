package com.taskmanagementsystem.backend.service;

import com.taskmanagementsystem.backend.dto.TaskDTO;
import com.taskmanagementsystem.backend.dto.UserDTO;

import java.util.List;

public interface AdminService {

    // Users
    List<UserDTO> getAllUsers();
    void deleteUser(Long userId);

    // Tasks
    List<TaskDTO> getAllTasks();
    TaskDTO updateTask(Long taskId, TaskDTO taskDTO);
    void deleteTask(Long taskId);
}