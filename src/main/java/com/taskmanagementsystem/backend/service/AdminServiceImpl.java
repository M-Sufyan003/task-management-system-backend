package com.taskmanagementsystem.backend.service;

import com.taskmanagementsystem.backend.dto.TaskDTO;
import com.taskmanagementsystem.backend.dto.UserDTO;
import com.taskmanagementsystem.backend.entity.Task;
import com.taskmanagementsystem.backend.entity.TaskStatus;
import com.taskmanagementsystem.backend.entity.User;
import com.taskmanagementsystem.backend.exception.TaskNotFoundException;
import com.taskmanagementsystem.backend.repository.TaskRepository;
import com.taskmanagementsystem.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TaskService taskService;

    public AdminServiceImpl(UserRepository userRepository,
            TaskRepository taskRepository,
            TaskService taskService) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.taskService = taskService;
    }

    // -----------------------
    // Users
    // -----------------------
    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    // -----------------------
    // Tasks
    // -----------------------
    @Override // for admin users to get task based on status
    public List<TaskDTO> getAllTasks(TaskStatus status) {
        List<Task> tasks;
        if (status != null) {
            tasks = taskRepository.findAll()
                    .stream()
                    .filter(task -> task.getStatus() == status)
                    .collect(Collectors.toList());
        } else {
            tasks = taskRepository.findAll();
        }

        return tasks.stream()
                .map(task -> taskService.mapToDTO(task)) // taskService must be TaskServiceImpl type
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO updateTask(Long taskId, TaskDTO taskDTO) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        // Admin can update any task
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setDueDate(taskDTO.getDueDate());

        Task savedTask = taskRepository.save(task);

        return taskService.mapToDTO(savedTask);
    }

    @Override
    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        taskRepository.delete(task);
    }
}