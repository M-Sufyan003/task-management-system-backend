package com.taskmanagementsystem.backend.controller;

import com.taskmanagementsystem.backend.dto.ChangeUserPasswordDTO;
import com.taskmanagementsystem.backend.dto.TaskDTO;
import com.taskmanagementsystem.backend.dto.UserProfileDTO;
import com.taskmanagementsystem.backend.dto.UserTaskStatsDTO;
import com.taskmanagementsystem.backend.entity.TaskStatus;
import com.taskmanagementsystem.backend.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Create a new task
    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskDTO taskDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        TaskDTO createdTask = taskService.createTask(taskDTO);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    // Get tasks of logged-in user
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getMyTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) TaskStatus status) {

        List<TaskDTO> tasks = taskService.getMyTasks(status, page);
        return ResponseEntity.ok(tasks);
    }

    // Update a task
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id,
            @Valid @RequestBody TaskDTO taskDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        TaskDTO updatedTask = taskService.updateTask(id, taskDTO);
        return ResponseEntity.ok(updatedTask);
    }

    // Delete a task
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Task deleted successfully");

        return ResponseEntity.ok(response);
    }

    // Get profile
    @GetMapping("/profile")
    public ResponseEntity<UserProfileDTO> getProfile() {
        return ResponseEntity.ok(taskService.getMyProfile());
    }

    // Update profile
    @PutMapping("/profile")
    public ResponseEntity<UserProfileDTO> updateProfile(@RequestBody UserProfileDTO dto) {
        return ResponseEntity.ok(taskService.updateMyProfile(dto));
    }

    // User stats
    @GetMapping("/stats")
    public ResponseEntity<UserTaskStatsDTO> getStats() {
        return ResponseEntity.ok(taskService.getMyTaskStats());
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changeUserPassword(@RequestBody ChangeUserPasswordDTO dto) {

        taskService.changeUserPassword(dto);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Password updated successfully");

        return ResponseEntity.ok(response);
    }
}