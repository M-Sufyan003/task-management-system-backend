package com.taskmanagementsystem.backend.dto;

import lombok.Data;

@Data
public class ChangeUserPasswordDTO {
    private String oldPassword;
    private String newPassword;
}
