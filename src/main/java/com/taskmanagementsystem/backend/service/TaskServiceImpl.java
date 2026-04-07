package com.taskmanagementsystem.backend.service;

import com.taskmanagementsystem.backend.dto.TaskDTO;
import com.taskmanagementsystem.backend.dto.UserDTO;
import com.taskmanagementsystem.backend.entity.Task;
import com.taskmanagementsystem.backend.entity.TaskStatus;
import com.taskmanagementsystem.backend.entity.User;
import com.taskmanagementsystem.backend.exception.TaskNotFoundException;
import com.taskmanagementsystem.backend.exception.UnauthorizedActionException;
import com.taskmanagementsystem.backend.repository.TaskRepository;
import com.taskmanagementsystem.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    // -----------------------
    // Helper methods
    // -----------------------
    private String getCurrentUserEmail() {
        var authentication = org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication();
        var userDetails = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        return userDetails.getUsername();
    }

    private User getCurrentUser() {
        String email = getCurrentUserEmail();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public TaskDTO mapToDTO(Task task) {
        User user = task.getUser();
        UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getEmail());

        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getDueDate(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                userDTO);
    }

    // -----------------------
    // Service methods
    // -----------------------
    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        User currentUser = getCurrentUser();

        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setDueDate(taskDTO.getDueDate());
        task.setUser(currentUser);

        Task savedTask = taskRepository.save(task);
        return mapToDTO(savedTask);
    }

    @Override // for normal users to get tasks based on status
    public List<TaskDTO> getMyTasks(TaskStatus status, int page) {

        User currentUser = getCurrentUser();

        Pageable pageable = PageRequest.of(page, 10); // max 10 records are allowed to send in tasks apis to achieve pagination

        Page<Task> taskPage;

        if (status != null) {
            taskPage = taskRepository.findByUserAndStatus(currentUser, status, pageable);
        } else {
            taskPage = taskRepository.findByUser(currentUser, pageable);
        }

        return taskPage.getContent()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO updateTask(Long taskId, TaskDTO taskDTO) {
        User currentUser = getCurrentUser();

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        if (!task.getUser().getId().equals(currentUser.getId())) {
            throw new UnauthorizedActionException("You are not authorized to update this task");
        }

        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setDueDate(taskDTO.getDueDate());

        Task savedTask = taskRepository.save(task);
        return mapToDTO(savedTask);
    }

    @Override
    public void deleteTask(Long taskId) {
        User currentUser = getCurrentUser();

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        if (!task.getUser().getId().equals(currentUser.getId())) {
            throw new UnauthorizedActionException("You are not authorized to delete this task");
        }

        taskRepository.delete(task);
    }
}