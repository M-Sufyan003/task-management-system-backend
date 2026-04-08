package com.taskmanagementsystem.backend.service;

import com.taskmanagementsystem.backend.dto.ChangeUserPasswordDTO;
import com.taskmanagementsystem.backend.dto.TaskDTO;
import com.taskmanagementsystem.backend.dto.UserProfileDTO;
import com.taskmanagementsystem.backend.dto.UserTaskStatsDTO;
import com.taskmanagementsystem.backend.entity.Task;
import com.taskmanagementsystem.backend.entity.TaskStatus;

import java.util.List;

public interface TaskService {

    TaskDTO createTask(TaskDTO taskDTO);

    TaskDTO mapToDTO(Task task);

    List<TaskDTO> getMyTasks(TaskStatus status, int page); // for normal users

    TaskDTO updateTask(Long taskId, TaskDTO taskDTO);

    void deleteTask(Long taskId);

    UserProfileDTO getMyProfile();

    UserProfileDTO updateMyProfile(UserProfileDTO dto);

    UserTaskStatsDTO getMyTaskStats();

    void changeUserPassword(ChangeUserPasswordDTO dto);
}