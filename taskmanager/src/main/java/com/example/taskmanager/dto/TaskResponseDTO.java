package com.example.taskmanager.dto;

import com.example.taskmanager.entity.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;
}
