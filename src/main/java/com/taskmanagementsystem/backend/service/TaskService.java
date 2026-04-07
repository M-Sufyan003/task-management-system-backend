package com.taskmanagementsystem.backend.service;

import com.taskmanagementsystem.backend.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    TaskDTO createTask(TaskDTO taskDTO);

    List<TaskDTO> getMyTasks();

    TaskDTO updateTask(Long taskId, TaskDTO taskDTO);

    void deleteTask(Long taskId);
}