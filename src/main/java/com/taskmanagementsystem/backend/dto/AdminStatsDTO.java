package com.taskmanagementsystem.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminStatsDTO {
    private long totalUsers;
    private long totalTasks;
    private long todoTasks;
    private long inProgressTasks;
    private long doneTasks;
}